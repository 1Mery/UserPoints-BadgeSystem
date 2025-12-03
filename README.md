🧩 User Points & Badge System

Kullanıcı puan & rozet yönetimi – Mikroservis Mimarisi

Bu proje, kullanıcıların yaptığı aksiyonlara göre puan kazandığı ve puanlara göre otomatik rozet aldığı bir mikroservis yapısıdır. Sistem tamamen event-driven çalışır ve Kafka ile haberleşir.

🚀 Mimari Özeti

🟦 User-Service 

Kullanıcı oluşturma

Puan ekleme

Profil bilgilerini rozet ile birlikte dönme

Badge-Service’ten rozet bilgisi alma

🟨 Action-Service 

Kullanıcı aksiyonu oluşturma

Kullanıcıya puan ekleme

Outbox Pattern ile Kafka’ya action-events gönderme

🟪 Badge-Service 

Kafka action-events tüketme

Kullanıcının toplam puanını User-Service’ten çekme

Puan eşiklerine göre rozet hesaplama

Kullanıcının rozetini güncelleme

User-Service’e “badge updated” bildirimi gönderme

🏅 Rozet Hesaplama Mantığı
if (points >= 20) PLATINUM;
else if (points >= 15) GOLD;
else if (points >= 5)  SILVER;
else                  BRONZE;


Her kullanıcı yalnızca bir güncel rozet taşır.

📡 Event Akışı (Kısa Özet)

Action-Service → Aksiyon oluşturur

User-Service → Kullanıcıya puan ekler

Outbox → Kafka’ya event yollar

Badge-Service → Event'i tüketip rozet belirler

Rozet DB’ye kaydedilir

User profili → rozet ile döner

🛠 Kullanılan Teknolojiler

Java 21

Spring Boot

Spring Cloud Stream (Kafka)

Feign Client

PostgreSQL

Docker Compose
