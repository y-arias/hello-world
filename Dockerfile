FROM store/oracle/serverjre:1.8.0_241-b07
MAINTAINER Carlos Tapia<carlos.tapia@sistemasa.cl>
RUN useradd springboot
USER springboot
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD ./target/lib           /usr/share/lib
# Add the service itself
#ARG JAR_FILE
COPY ./target/ms-domain-score.jar /usr/share/app.jar
EXPOSE 8084
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/usr/share/app.jar"]