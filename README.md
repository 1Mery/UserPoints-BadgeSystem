<h1 align="center">🧩 User Points & Badge System</h1>
<p align="center"><i>Kullanıcı puan & rozet yönetimi — Event-Driven Mikroservis Mimarisi</i></p>

<br/>

<h2>🚀 Mimari Özeti</h2>

<ul>
  <li><b>🟦 User-Service </b>
    <ul>
      <li>Kullanıcı oluşturma</li>
      <li>Puan ekleme</li>
      <li>Profil bilgisini <b>rozet ile birlikte</b> döndürme</li>
      <li>Badge-Service ile Feign üzerinden iletişim</li>
    </ul>
  </li>

  <li><b>🟨 Action-Service </b>
    <ul>
      <li>Kullanıcı aksiyonlarını toplama</li>
      <li>Kullanıcıya puan ekleme</li>
      <li><b>Transactional Outbox Pattern</b> ile Kafka’ya event gönderme</li>
    </ul>
  </li>

  <li><b>🟪 Badge-Service </b>
    <ul>
      <li>Kafka <code>action-events</code> tüketme</li>
      <li>Kullanıcının toplam puanını User-Service üzerinden çekme</li>
      <li>Puan eşiklerine göre doğru rozeti hesaplama</li>
      <li>Eski rozetleri silip yeni rozeti kaydetme</li>
      <li>User-Service’e “badge updated” bildirimi gönderme</li>
    </ul>
  </li>
</ul>

<br/>

<h2>🏅 Rozet Hesaplama Mantığı</h2>

<pre><code>
if (points >= 20) PLATINUM;
else if (points >= 15) GOLD;
else if (points >= 5)  SILVER;
else                  BRONZE;
</code></pre>

<p>Her kullanıcı sistemde <b>yalnızca bir güncel rozet</b> taşır.</p>

<br/>

<h2>📡 Event Akışı (Kısa Özet)</h2>

<ol>
  <li>Action-Service: Aksiyon oluşturur</li>
  <li>User-Service: Kullanıcıya puan ekler</li>
  <li>Outbox: Kafka’ya event yollar</li>
  <li>Badge-Service: Event’i dinler ve rozet belirler</li>
  <li>Rozet DB’ye kaydedilir</li>
  <li>User profil endpoint'i → rozet ile birlikte döner</li>
</ol>

<br/>

<h2>🛠 Kullanılan Teknolojiler</h2>

<ul>
  <li>Java 21</li>
  <li>Spring Boot</li>
  <li>Spring Cloud Stream (Kafka)</li>
  <li>Feign Client</li>
  <li>PostgreSQL</li>
  <li>Docker Compose</li>
</ul>

<br/>
