package Groupld.Server.Util;

import Groupld.Server.Server;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class JWTService {

    private long DEFAULT_EXPIRE_IN_SECONDS = 60*2; // 2 минуты
    private  Key key;
    private PublicKey publicKey;

    public JWTService(){
        String jksPassword = "gruzia123";
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream("/Users/kamiliskhakov/IdeaProjects/laba7/Server/keyStore/hanbotest.jks"), jksPassword.toCharArray());
            Key key = ks.getKey("hanbotest", jksPassword.toCharArray());
            this.key = key;
            this.publicKey = loadPublicKey("/Users/kamiliskhakov/IdeaProjects/laba7/Server/keyStore/hanbotest.cer");
        } catch (Exception e) {
            Server.LOGGER.info("exception with JWT token" );
        }
    }

    public String generateJWTToken(String username) {
        long now = new Date().getTime();
        long expireTime = now + (DEFAULT_EXPIRE_IN_SECONDS * 1000);
        Date expireDate = new Date(expireTime);
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setAudience("Lab7")
                .setExpiration(expireDate)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.RS512, key)
                .compact();
        return jwtToken;
    }

    public static PublicKey loadPublicKey(String filename)
            throws Exception
    {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate cert = cf.generateCertificate(new FileInputStream(filename));
        PublicKey retVal = cert.getPublicKey();
        return retVal;
    }
    public boolean verifyJWTToken(String token){
        Jws<Claims> x =  null;
        try {
             x =Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        }catch (Exception e){
            Server.LOGGER.info( "has not correct JWT token" );
            return false;
        }
        Server.LOGGER.info(x.getBody().getSubject() + "gets correct JWT token" );
        return true;
    }
    public String decryptUserJWTToken(String token) {
        Jws<Claims> x = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Server.LOGGER.info("got user login from JWT token" );
        return x.getBody().getSubject();
    }

}