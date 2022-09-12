#Pull base image
#-----------------
FROM openjdk:11

#Author
#-------
LABEL org.opencontainers.image.authors="Jennifer Reif,jennifer@thehecklers.org,@JMHReif"

#Copy jar and expose entrypoints
#--------------------------------
COPY target/service2-*.jar goodreads-svc2.jar
ENTRYPOINT ["java","-jar","/goodreads-svc2.jar"]