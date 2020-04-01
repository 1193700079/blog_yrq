FROM java:8
VOLUME /tmp
ADD blog-0.0.1-SNAPSHOT.jar yrqblog.jar
EXPOSE 9090
ENTRYPOINT [ "java",  "-Djava.security.egd=file:/dev/./urandom", "-jar","/yrqblog.jar" ]