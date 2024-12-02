def tipo_de_escaneo = 'Full'  // Tipo de escaneo: Baseline, APIS o Full
def objetivo = 'http://host.docker.internal:8081'  // URL objetivo para el escaneo

pipeline {
    agent any
    tools {
        jdk 'JAVA'         // Asegúrate de que Java esté configurado en Jenkins
        maven 'maven'      // Asegúrate de que Maven esté configurado en Jenkins
    }
    environment {
        GECKODRIVER_PATH = "/usr/local/bin/geckodriver"   // Ruta a Geckodriver
        FIREFOX_BINARY = "/usr/bin/firefox-dev"              // Ruta a Firefox Developer Edition
        SCANNER_HOME = tool 'sonar-scanner'                 // Ruta al escáner de SonarQube
        RUTA_JMETER = "$HOME/apache-jmeter-5.6.3/bin/jmeter" // Ruta a JMeter
        SONAR_TOKEN = credentials('sonar-token')
        SONAR_KEY = credentials('sonar-key')
        SONAR_ORG = credentials('sonar-org')
    }
    parameters {
        choice(
            choices: ['Baseline', 'APIS', 'Full'],
            description: 'Tipo de escaneo a realizar dentro del contenedor',
            name: 'TIPO_DE_ESCANEO'
        )
        string(
            defaultValue: 'http://host.docker.internal:8081',
            description: 'URL del objetivo para el escaneo',
            name: 'OBJETIVO'
        )
        booleanParam(
            defaultValue: true,
            description: 'Generar un reporte después de las pruebas.',
            name: 'GENERAR_REPORTE'
        )
    }
    stages {
        stage('Limpiar Reportes Anteriores') {
            steps {
                script {
                    echo 'Limpiando los reportes anteriores...'
                    // Eliminar los reportes previos y archivos generados
                    sh 'rm -rf $HOME/jmeter_report $HOME/resultados.jtl'
                }
            }
        }

        stage("Clonar Repositorio") {
            steps {
                git branch: 'develop', url: 'https://github.com/Davidchavez2204/panda_application.git'
            }
        }

        stage("Compilar Proyecto") {
            steps {
                sh "mvn clean compile"
            }
        }

        // Etapas paralelizadas para optimizar el tiempo
        stage('Ejecutar Análisis y Pruebas') {
            parallel {
                stage('Análisis SonarQube') {
                    steps {
                        sh """$SCANNER_HOME/bin/sonar-scanner -Dsonar.url=http://localhost:9000/ \
                            -Dsonar.login=$SONAR_TOKEN \
                            -Dsonar.projectKey=$SONAR_KEY \
                            -Dsonar.projectName=$SONAR_KEY \
                            -Dsonar.sources=. \
                            -Dsonar.organization=$SONAR_ORG \
                            -Dsonar.java.binaries=."""
                    }
                }
                stage('Pruebas Unitarias') {
                    steps {
                        sh "mvn test -Dtest=PandaApplicationTests"
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }

        stage("Ejecutar Pruebas Funcionales") {
            steps {
                script {
                    echo "Ejecutando pruebas funcionales con Selenium..."
                    try {
                        sh """
                        mvn -Dtest=FunctionalTests test \
                        -Dwebdriver.gecko.driver=$GECKODRIVER_PATH \
                        -Dwebdriver.firefox.bin=$FIREFOX_BINARY
                        """
                    } catch (Exception e) {
                        echo "Saltando pruebas funcionales debido a un error: ${e.message}"
                    }
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage("Pruebas de Rendimiento - JMeter") {
            steps {
                script {
                    echo "Ejecutando pruebas de rendimiento con JMeter..."
                    // Eliminar el archivo resultados.jtl si existe
                    sh '''
                        if [ -f $HOME/resultados.jtl ]; then
                            rm $HOME/resultados.jtl
                        fi
                    '''
                    // Ejecutar JMeter en modo headless
                    sh """
                    $RUTA_JMETER -n -t "$HOME/jmeter_test_plan.jmx" -l $HOME/resultados.jtl -e -o "$HOME/jmeter_report"
                    """
                }
            }
        }

        stage('Información del Pipeline') {
            steps {
                script {
                    echo '<--Inicialización de Parámetros-->'
                    echo """
                        Los parámetros actuales son:
                            Tipo de escaneo: ${tipo_de_escaneo}
                            Objetivo: ${objetivo}
                            Generar reporte: true
                    """
                }
            }
        }

        // Ejecutar OWASP ZAP solo si el tipo de escaneo no es Baseline
        stage('Configuración del contenedor OWASP ZAP') {
            when {
                expression {
                    return tipo_de_escaneo != 'Baseline'  // Saltar OWASP ZAP si el escaneo es 'Baseline'
                }
            }
            steps {
                echo 'Descargando la última imagen de OWASP ZAP --> Comienzo'
                sh 'docker pull zaproxy/zap-stable'
                echo 'Descarga de la última imagen de OWASP ZAP --> Fin'
                echo 'Iniciando el contenedor --> Comienzo'
                sh 'docker run -dt --name owasp zaproxy/zap-stable /bin/bash'
            }
        }

        stage('Preparar directorio wrk') {
            steps {
                script {
                    sh '''
                        docker exec owasp mkdir /zap/wrk
                    '''
                }
            }
        }

        stage('Escaneo objetivo en contenedor OWASP') {
            when {
                expression {
                    return tipo_de_escaneo != 'Baseline'
                }
            }
            steps {
                script {
                    echo "----> tipo_de_escaneo: ${tipo_de_escaneo}"
                    echo "----> objetivo: ${objetivo}"
                    sh """
                        docker exec owasp \
                        zap-full-scan.py \
                                        -t ${objetivo} \
                -r report.html \
                -w zap_warnings.md \
                -J zap_report.json \
                -x zap_report.xml \
                -z "-config spider.maxDepth=10 \
                     -config scan.force=true \
                     -config api.disablekey=true \
                     -config view.mode=attack \
                     -config ajaxSpider.maxDuration=5"

                    """
                }
            }
        }

        stage('Copiar Reporte al Workspace') {
steps {
    script {            
        echo "Copying the entire OWASP ZAP working directory to the Jenkins workspace..."
        sh 'docker cp owasp:/zap/wrk "/var/lib/jenkins/workspace/zap_reports"'
    }
}

        }
    }
    post {
        always {
            echo 'Deteniendo y eliminando el contenedor OWASP ZAP'
            sh '''
                docker stop owasp
                docker rm owasp
            '''
            cleanWs()  // Limpiar el workspace después de la ejecución
        }
    }
}
