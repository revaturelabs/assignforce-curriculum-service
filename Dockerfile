FROM java:8
ADD target/curriculum-service.jar .
EXPOSE 8661
CMD java -jar -Xmx512M curriculum-service.jar