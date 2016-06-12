package com.thetminko.gettogether.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Created by developer on 12/6/16.
 */
public class Encryption {
  private static final Logger LOG = LoggerFactory.getLogger(Encryption.class);

  /**
   * Encode byte to String using base64
   *
   * @return encoded string
   */
  public static String encodeByteToString(byte[] bytes) {
    Base64.Encoder encoder = Base64.getEncoder();
    return encoder.encodeToString(bytes);
  }

  /**
   * Decode string to bytes
   *
   * @return decoded string
   */
  public static byte[] decodeStringToBytes(String encodedString) {
    Base64.Decoder decoder = Base64.getDecoder();
    return decoder.decode(encodedString);
  }

  public static String getUuid() {
    return String.valueOf(UUID.randomUUID());
  }

  public static byte[] generateSecureSalt(int saltBytes) {
    LOG.debug("Starting to generate secure salt");
    byte salt[] = new byte[saltBytes];
    try {
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(salt);
      LOG.debug("Generated secure salt successfully");
    } catch (Exception e) {
      LOG.error("Failed to generate secure salt", e);
    }
    return salt;
  }

  /**
   * generate secure hashed password
   *
   * @return hashedPasswored byte[]
   */
  public static byte[] generateHashedPassword(final char[] password, final byte[] salt,
                                              int hashIterations, int hashKeyLength) {
    LOG.debug("Starting to hash password");
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, hashIterations, hashKeyLength);
      SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
      byte[] hashedPassword = secretKey.getEncoded();
      LOG.debug("Hashing password completed successfully");
      return hashedPassword;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      LOG.error("Failed to hash password", e);
      return new byte[0];
    }
  }
}
