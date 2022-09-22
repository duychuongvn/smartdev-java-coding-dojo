package encryption;


import sun.security.rsa.RSAPrivateCrtKeyImpl;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.*;
import java.util.Base64;

public class PrivateKeyReader {

    public static void main(String[] args) throws Exception {
        System.out.println( get(""));
    }
    public static PrivateKey get(String filename)
            throws Exception {
        String  key = "MIIEpAIBAAKCAQEA4v2xNDUJv82MOvNl3VKo9w3W6ekYDsMn8FEgQMa06qyZDo9w\\nc4u2K5b7NmcCWGgqvdTTmiLc3s2cQ1nXq+Xm1dpYm2lljSWWZHNjHPSRh3X8yw4N\\nKoewelYZZJGluv9nvUzbXbYBC4Jzo6WWFtqWcGSCY+HV9vfGKNI4gWmD3TviJkxr\\nduUYuYzh2qcSmbm9aa4Xd4Wa2ib8heSRDCuWoPWMztcYD/Lzxs/4Ib4WF1Xn5ztl\\nw/4KpwZcyRExCx0aZlMF03XLUPXAOyvoUTLZEyW83Fc+/L/ZZM0auqGb2vsOXEbI\\nvCPW/rVmXCr6gDh2nO3MjR8gSDRMNDHxKSPhGQIDAQABAoIBAGN675iDzhwDR3vm\\nhDQ1L9vmctPc1jBSE0TdKRd6fnuC3Slmb9AT4OjFqJ81EfAQ5vp/NaWNteLnlZ5m\\n14B8p2f942tpC5qKaJjpgPyFquoFKZ8ML3T+NrfG2CYdNtb3bE2oS1OMmnSi3W9Q\\n+LBXaCslwYdoyE8OVAg76Yu95I6mBp/HOzucSNrx4Gfs6wCzNWLCpzQo9xsnLlIw\\nmLFcyjaMlgiqBTlghZ65dccfUJ2CgrKMfbUu193aldCMsrrvued1u7w4XH6uvT0L\\nfNat9cWEhzVE5Aws9sf2qbBavaqHJxNaZ5GamPZUuRYYX6pa15yrBciMWbEZSPFh\\nASi5yAECgYEA6vvcOv8/PfVjrdWMGoo5F1sYjKOa7fK1wC323SBU+hHfY4RdcEKF\\nUEs3as3flDvHzQ6UN5rO1SDRe79VsJCeKkxi7hXDdxzTewNOywxcQAIruVZf5sOC\\nhCVyO5gUAzvp8kwSKgjYHyJFDY/dPfhgMBvZavcc+2akHG7vvcg3fYECgYEA90rU\\nWE0VY1aqso0SGeXXVgSsKfQpm6ErYU1wSeRo0xzQfer7BSmyvchvNX5SM2FGsRxK\\n1sxb9gD4eDFffXyqo9AkJulQ68TxFsiEBU8nBmp/FCy10ncWRIAjys3gIXFfEQZD\\nY7bApu+WO4CnmYgXAW6/r4HqzsAhc2WNDCnnX5kCgYEAxD71rXvwiEFBLzOyX5Pi\\nnEPjG8mjIWhbLBX3HrPa5EI663TnSFL13ZoDvToUqErI0dOEK3ApnstayFuCKASP\\nY0oKMUaRyQ+U+x5HFsLK5FYryAx/6UyM2uofHfAjfpPyDP5FCT/7pvzK6MmmfxPI\\n2UTY7kFUvgz5sBliNYBZ/4ECgYB1tH5iET6u69+P4SnOfeIDJXI0/eyYERyu0ccy\\nxK9IXRy38pLMoE5dmvukAJ5rqk+VmZvdpaPX8hpSS13iQuaN1lJ+2usTbcg7SfHc\\n/RHcMfCOz8Ezjk9hB4UB0QBvqqDPX6XP3nrQepWNeO1NXMNmgSpoBXSYOEczwBBk\\ny8U/cQKBgQDARNBpxy0FPFKwfgS5+WGDD7eI0UJu7Lywy+Wg5gEKtbwUh6om4Muh\\n36npqOJBWPv9/fQXSm90Vuv/bnKLFIzbj9YfPuZk1XprdS2bVPYWoe7bCFKP3kKc\\nvM35bGgWsYvz+ddXy3MGQUPomM8HF2rFtjXdG7rKe+zCidHSKaTlww==";

//        String key = "MIIJRQIBADANBgkqhkiG9w0BAQEFAASCCS8wggkrAgEAAoICAQD3tnGlbrmxoQ8s\n" +
//                "hYvkbXfcmudGLM5Q5gY97C3w9jOP6vIKIjTY61FZDIwpoOGEfhZECYOJl+uXNa5i\n" +
//                "CRnwpRVGROmZ7dl3m5WmM93zWhSKm4Yix/AEkA0pKTF4G5QxoJP5VlAr+VNDW7za\n" +
//                "MtvzZ/EAUGNr5HopFX/if3iZUSLVAoJGuKq+yZlXUu3Zk8182lfuRtmlHfgrQMSK\n" +
//                "N12B1QZaDAr/Mfh99VqSdlXKsWWnVeov+Exl8ezC8JpwJhDkFi1Lq8OmK1fLDvDr\n" +
//                "+OGMfG+psACN0s6mwEIFFiMHyK4FZVieVV3KH3cCJAyaN45MtUDuypUbkX+hf6MT\n" +
//                "QmOHb0b0C8YNsG8tAQWiofIuZlHd41VaVt2Sg9RGoLjpjwKB0uiaFOe6eonsPs0z\n" +
//                "uIvYa1bzMIou4dfTxCRX2pEdz0P9c/keoJK1fp0h8Hpt/lcEon/jyfxVltx0C4it\n" +
//                "QW0+gTE30k0BMPPTwYDFYtLj8JceH0wLi0IrUsewphEB0F97f2X5RHoiNmgRiwQB\n" +
//                "WTSnGAuam9GGbyBEVPSqIqF/3qrNPqIx9kEsqjkesdrsSHbZZOH5UKukOvYLNPDN\n" +
//                "75JNUJCVMGadrqHgI6NekTrsHwCfFk1xg4Qle6c4kqrUWjCQAj+eXmXZwMht4uKP\n" +
//                "FUzrz/osLJU/Oj3TSw9/1LJ8v8+LPQIDAQABAoICAQDpdosftv7OuhQ6Ctew/7cz\n" +
//                "NM96ZSpCNrl1TQ5zhydLocxi3qBePVKbEedcKKPDoq5Li4fse0FwbwienyNSmdQ2\n" +
//                "OSbNi80lHH7reQDbpW+svy2pxDSjqDyvKYLVR3DhYQ11tvKyrzbZSjHwmHSSsMkE\n" +
//                "Wb+KZXj5FalFCIzaOw0QI2AzVUKftBOQqLlBEjteS88J5L6mD1vhMBbMiafYgLSO\n" +
//                "08DdifRAiqjFV0A+W/mJv8CLW4JtpphVljyILGhI/9nK8uTfzQPefoN0c0nohTMc\n" +
//                "g6PSBvf7U0Il1xpG8M2Kilz+RUizAfrBCsbR5kB4bW/4XvgvVm8q/8frk+uILsl3\n" +
//                "yJddAzStUWrVJa8wHfaH4DBS3QJ7dm8IrUXg63kYw8sJNLF7YO5XVlWE03orPnau\n" +
//                "glDnJeYAfZuq/b4vURO3O4qgSrljy+gDsUKRBXqDmDk0BobYUoyPn+HEvQT3tdNa\n" +
//                "MNNBhZ8GsDX+u/1KadssRpEDZGpf3DyGoWdIAplrdJtSLj8rW8t3LI/hq8vp1SJK\n" +
//                "VKfGuXAeHYXzdeuM+i+Vgm7DSGZKnjdSn25g3rKrDZVVCGqbCQdxzzJiZlgs9efx\n" +
//                "NhtMIaanlw5HgE2J54LyAgjstUjoqYsux/KGoHvWX/aF7gY6XCrGnw0IkSnISn9l\n" +
//                "U1LFhUYlZE5a98kSo1wRRQKCAQEA/qWyukXjYkxEWCXTI4zUxCnXN6KJ7WtlY7YT\n" +
//                "3wKfcD/xNMXlgLidfDwngScmMYbzTaD+9OGicCt/x9CK+zF0VYMapEyQmYTAu/xE\n" +
//                "pQ+oDrED9/Z8OK51qkKkzj/vA+7+XKXbAcPkKbm52CQasxGicHDNPEM4pltZMBp3\n" +
//                "xehRkRI3uo2kugPs3uDGnlFkMZz+jb4J43bJWDAqTlGar7JDEuVW6aNh3R0/V7kr\n" +
//                "AXRtyMnWxglbQQfl5wLpn6E1J+71qqxqw83AChjTqT8+axX2XdgUu3yns+c+Ziho\n" +
//                "Gej0sIO5MuKukyY7KQBjfZe7HsqZdIk8VjP3F6JsvV4EEPrE2wKCAQEA+QdQs4iq\n" +
//                "TyaxQ+yJOmDYNEC1HwSOC1THLP53KxLYTED/tucjzOl1CW10AoMm5NgdZE/nvnRV\n" +
//                "DEDKvQQicj9BMUDUGOGjrV8r2KKCMT93Qd67BHR5M/vgWKfBkue6UUJfo5PVduYt\n" +
//                "W+bUjzZ0Osq1wRQ9fpTDDsgMyJYPL9ka0/GKyDDguothYNzuh1J+8E/zMyHzGebH\n" +
//                "8RmonfFteMJWeq7eaO5Vk8B8Cj2KCJkEBcN7n2VQ5e0n5GLSOfCXc6QPRwJPWGrF\n" +
//                "yStjbSdCGruIYEg9u7V8gHnJdjUl/d1V3WDwJZKPZbr0a8JRAEMpcBq2y3W5bFsm\n" +
//                "VWeU8wxDJj4fxwKCAQEA8MCmBriWr1Pd9dzlDyJR8piAUqhIcQBtnmUByoGHTjAW\n" +
//                "E0faoOVqd4/D5bamxIKLO2SwR8OfuR9XMwn2rGDoVQSbxqRgvdQmCFUMbWYtBJ7V\n" +
//                "WrVeYpSGyI8zk5AcoQJu2BcyxSt4Zv6iNziDSbOteVMcpxIC2D1bSEBt5t2raFXp\n" +
//                "/GV4TSny5BFtlzbqXIHKpSX74WNoHwmCpgM/FUebZJ0Rm9kNaGkDfyOhBkIWnZQ+\n" +
//                "i4NDFGusL8mwKWzXfcbN2ywh0LeRDbrFOqmBkOr1v2nQyCWlxFAnzZv8OCroVI5l\n" +
//                "JTftx0+I9U3Zwl8W2ZjAls49Dl8ZCO5Wkn71pzv/qwKCAQEAxejeZhBS6ZyifIzb\n" +
//                "cp3MtZpHV2NTUAYMVOHW61CKve9aSo3fkdZnwRpPfD+nBXALy9EFKANkI9zKQvWq\n" +
//                "Oomy0eXnqe6JGhvsyLo+Jc1+hcbxclTujjW60jpgSNQI6tOJFoNCvr+nhMYqX7az\n" +
//                "IJZBZDUluheX/YNXRYKchzZPb+kiln7h2t8ygLyUhM83Ot0PNq/gd9FFTYNK0lsw\n" +
//                "2Sv5dn7LGzFuqYHsEmdd7/9LhxkYglEOveyAK62tzYY7lxfnXFOlo2sXzQzCc9kj\n" +
//                "XAN2Gq7PAQL281PWnC8ZBmyE9O85a2LSu9KctZc0sLSvlfxlEcV5m3fFO1KauZ4h\n" +
//                "L59nuQKCAQEAugNPyEaYGipFF/82hr5SW5Qs1KTOjXQImk9FCfelcnGuPFqJQ98V\n" +
//                "lqqYTlBKX9RWEoLZkBGmClmMqbDIkmlLmdYtmbS1JTY2cQh4tgTeiGYtFsY9LKGN\n" +
//                "CDUZ7XX8+IE6VbyplTAGpR/3WukDPQMLXio2SunXOpYGP+G4wjB0Gz+7E7Iy6O2m\n" +
//                "ES7k2prN5ZEXFZ/G6Yig3Rsecu3DqcDv28S6m+TdTUAEmcZ6C9cJ0jJ+d5313VHG\n" +
//                "+5ifU2XHo/THL/wlZxvHC9+08E8mq/rqbtUOPnUR9wRBTqfFQQ0O56DIvlXL6W+P\n" +
//                "ZvjI6Xrwx/ioO3PAKt6SGk/SoiyFHEM4Tg==";
//        String key = "MIIEpAIBAAKCAQEA4v2xNDUJv82MOvNl3VKo9w3W6ekYDsMn8FEgQMa06qyZDo9w\\nc4u2K5b7NmcCWGgqvdTTmiLc3s2cQ1nXq+Xm1dpYm2lljSWWZHNjHPSRh3X8yw4N\\nKoewelYZZJGluv9nvUzbXbYBC4Jzo6WWFtqWcGSCY+HV9vfGKNI4gWmD3TviJkxr\\nduUYuYzh2qcSmbm9aa4Xd4Wa2ib8heSRDCuWoPWMztcYD/Lzxs/4Ib4WF1Xn5ztl\\nw/4KpwZcyRExCx0aZlMF03XLUPXAOyvoUTLZEyW83Fc+/L/ZZM0auqGb2vsOXEbI\\nvCPW/rVmXCr6gDh2nO3MjR8gSDRMNDHxKSPhGQIDAQABAoIBAGN675iDzhwDR3vm\\nhDQ1L9vmctPc1jBSE0TdKRd6fnuC3Slmb9AT4OjFqJ81EfAQ5vp/NaWNteLnlZ5m\\n14B8p2f942tpC5qKaJjpgPyFquoFKZ8ML3T+NrfG2CYdNtb3bE2oS1OMmnSi3W9Q\\n+LBXaCslwYdoyE8OVAg76Yu95I6mBp/HOzucSNrx4Gfs6wCzNWLCpzQo9xsnLlIw\\nmLFcyjaMlgiqBTlghZ65dccfUJ2CgrKMfbUu193aldCMsrrvued1u7w4XH6uvT0L\\nfNat9cWEhzVE5Aws9sf2qbBavaqHJxNaZ5GamPZUuRYYX6pa15yrBciMWbEZSPFh\\nASi5yAECgYEA6vvcOv8/PfVjrdWMGoo5F1sYjKOa7fK1wC323SBU+hHfY4RdcEKF\\nUEs3as3flDvHzQ6UN5rO1SDRe79VsJCeKkxi7hXDdxzTewNOywxcQAIruVZf5sOC\\nhCVyO5gUAzvp8kwSKgjYHyJFDY/dPfhgMBvZavcc+2akHG7vvcg3fYECgYEA90rU\\nWE0VY1aqso0SGeXXVgSsKfQpm6ErYU1wSeRo0xzQfer7BSmyvchvNX5SM2FGsRxK\\n1sxb9gD4eDFffXyqo9AkJulQ68TxFsiEBU8nBmp/FCy10ncWRIAjys3gIXFfEQZD\\nY7bApu+WO4CnmYgXAW6/r4HqzsAhc2WNDCnnX5kCgYEAxD71rXvwiEFBLzOyX5Pi\\nnEPjG8mjIWhbLBX3HrPa5EI663TnSFL13ZoDvToUqErI0dOEK3ApnstayFuCKASP\\nY0oKMUaRyQ+U+x5HFsLK5FYryAx/6UyM2uofHfAjfpPyDP5FCT/7pvzK6MmmfxPI\\n2UTY7kFUvgz5sBliNYBZ/4ECgYB1tH5iET6u69+P4SnOfeIDJXI0/eyYERyu0ccy\\nxK9IXRy38pLMoE5dmvukAJ5rqk+VmZvdpaPX8hpSS13iQuaN1lJ+2usTbcg7SfHc\\n/RHcMfCOz8Ezjk9hB4UB0QBvqqDPX6XP3nrQepWNeO1NXMNmgSpoBXSYOEczwBBk\\ny8U/cQKBgQDARNBpxy0FPFKwfgS5+WGDD7eI0UJu7Lywy+Wg5gEKtbwUh6om4Muh\\n36npqOJBWPv9/fQXSm90Vuv/bnKLFIzbj9YfPuZk1XprdS2bVPYWoe7bCFKP3kKc\\nvM35bGgWsYvz+ddXy3MGQUPomM8HF2rFtjXdG7rKe+zCidHSKaTlww==";
//        byte[] keyBytes =  Files.readAllBytes(Paths.get(filename));
        byte[] keyBytes =  key.getBytes(StandardCharsets.UTF_8);
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );

        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PublicKey pub =  kf.generatePublic(spec);
//
//        System.out.println(pub);
        String keyContent  = key.replace("\\n", "");


        System.out.println(keyContent);
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyContent));

        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);

        RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey)privKey;
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPublicExponent()));

        String secretMessage = "Baeldung secret message";

//        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);


        Cipher encryptCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParameterSpec);

        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println("vault:v1:"+ encodedMessage);
//        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Cipher decryptCipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
//        decryptCipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey, oaepParameterSpec);
        decryptCipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

//        String chiper = "dHKnWyXDZePwMCUrkIsOwO01lwBTNSAf39GcG6YdHKutpptzhyiFmu4qIySvBFjr2V8llaPMUyBFzaCcyPlrDE/ZP9GYQ3QN0f/SaN4DrZ+XbmBmnFTcJS/7KD/EeU0QLYMpIfhCpi0J8fM7EwDeTKF0y3cMq0hW048q/MRiPJuCYEmBakqU6B4vPrRPcDz/vz5XFfp66Gkhq6aOjjiniBNz4VuUsp+X/j05sVhvlvy1zW3l7mlGxwkgsbXbmYqq8xu3wndByBzmsdZ+SI1oFMuCzLyCVsKVkU5FNwRKCYJdPWaIWL4NrHPTkpZ8PDcAeA0gGPG7+hiCGz5zgJ6k0w==";
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encodedMessage.getBytes(StandardCharsets.UTF_8)));
//        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        System.out.println(decryptedMessage);

        System.out.println("---PUB--");
        System.out.println(new String(Base64.getEncoder().encode(publicKey.getEncoded())));
        System.out.println("-----");

//        System.out.println(publicKey);
//        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
//        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
//        return kf.generatePrivate(spec);
        return privKey;
    }


}