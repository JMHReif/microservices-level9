#Pull base image
#-----------------
FROM openjdk:11

#Author
#-------
LABEL org.opencontainers.image.authors="Jennifer Reif,jennifer@thehecklers.org,@JMHReif"

#Copy jar and expose entrypoints
#--------------------------------
COPY target/service1-*.jar goodreads-svc1.jar
ENTRYPOINT ["java","-jar","/goodreads-svc1.jar"]