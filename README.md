Proje Adı: ONLINE ORDER TRACKING SYSTEM

Ön Gereksinimler Projeyi çalıştırmak için aşağıdaki yazılım ve araçlar yüklü olmalıdır:

Java JDK 8 veya üstü Apache Maven veya Gradle PostgreSQL veritabanı

Kurulum Projeyi bilgisayarınıza klonlayın. https://github.com/ezgi02/ONLINE-ORDER-TRACKING-SYSTEMS.git

Kullanım Uygulama başlatıldıktan sonra tarayıcınızdan http://localhost:8090 adresine giderek uygulamayı kullanabilirsiniz.

Proje dizinine gidin. cd proje

application.properties içerisine aşağıdaki PostgreSQL bağlantı bilgilerini ekleyin: 
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect spring.jpa.hibernate.ddl-auto=update spring.jpa.hibernate.show-sql=true spring.datasource.url=jdbc:postgresql://localhost:5432/Northwind spring.datasource.username=postgres spring.datasource.password=12345 spring.jpa.properties.javax.persistence.validation.mode=none server.port=8090

Projeyi derleyin ve çalıştırın. mvn spring-boot:run
