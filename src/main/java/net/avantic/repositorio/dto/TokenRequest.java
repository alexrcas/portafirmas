package net.avantic.repositorio.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.crypto.Cipher;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TokenRequest {

    @FormParam("usuario")
    @PartType(MediaType.TEXT_PLAIN)
    private String usuario;

    @FormParam("grupo")
    @PartType(MediaType.TEXT_PLAIN)
    private String grupo;

    @FormParam("authRequest")
    @PartType(MediaType.TEXT_PLAIN)
    private String authRequest;

    private final String publicKeyString = "-----BEGIN PUBLIC KEY----";

    public TokenRequest(Credenciales credenciales) {
        this.usuario = credenciales.getUsuario();
        this.grupo = credenciales.getGrupo();
        this.authRequest = firmarToken(credenciales);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getAuthRequest() {
        return authRequest;
    }

    public void setAuthRequest(String authRequest) {
        this.authRequest = authRequest;
    }


    private String firmarToken(Credenciales credenciales) {
        try {
            ObjectMapper JSON_MAPPER = new ObjectMapper();
            return encrypt(JSON_MAPPER.writeValueAsString(credenciales));
        } catch (Exception e) {
            throw new RuntimeException("Error cifrando token");
        }
    }

    private String encrypt(String message) throws Exception{

        String publicKeyString = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAogwDJ2yhGHRqlqRNYuDsR3hR7WFbHCaPyX+gu/IIE0FU/CZ3iVbOYgeJDHmYXo00X23n4wLXcT4Qwhw9dq4XG3j9nkAc5FZRUAl0QPKN6DNoDmd0DQAwvMzue8RiXt9eq6c25UADaDaqPHWQSSBzyJ64ZmHcGMxFcTRfdvH1+9m1HBB0goX/TcGf03iQeNBuqpoRpV/Qfn2gxkXWNd3P8uQ+BJlekxk1VbIj5OKZ7eBu04z9IWjM/117rVGN+yntjDmLU87w/wNF3lSK44p0qY/KJh3dcNirFjo6RidI3XR74t0OHjeurTxMKcWRAZpnSSAzMbpERFAJW99X/GQKFQIDAQAB-----END PUBLIC KEY-----";

        String publicKeyPEM = publicKeyString
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] messageToBytes = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        PublicKey publicKey = readKey(publicKeyPEM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    
    private PublicKey readKey(String publicKey) {
        try {
            byte[] encoded = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubkey = keyFactory.generatePublic(keySpec);
            return pubkey;
        } catch (Exception e) {
            throw new RuntimeException("Error en método readKey");
        }

    }


}
