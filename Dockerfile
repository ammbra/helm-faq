FROM maven:3.6.1-jdk-8 AS MVN_BUILD

COPY . /code
RUN echo '{ "allow_root": true }' > rm -Rf /code/target && \
	cd /code/ && \
	chmod +x mvnw && \
    mvn clean package 
        
FROM openjdk:8-jre
COPY --from=MVN_BUILD /code/target/*.jar /faq.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","faq.jar"]
