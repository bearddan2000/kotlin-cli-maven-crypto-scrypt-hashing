package example
import org.bouncycastle.crypto.generators.SCrypt;

val SALT: String = "abc123";

// DifficultyFactor
// These should be powers of 2
val cpu: Int = 8;
val memory: Int = 8;
val parallelism: Int = 8;
val outputLength: Int = 32;

fun hashPsw(plainText: String): String {
    val hash = SCrypt.generate(plainText.toByteArray(), SALT.toByteArray(), cpu, memory, parallelism, outputLength);
    return String(hash);
}

fun checkPsw(plainText: String, hashedStr: String):  Boolean {
    val hash = SCrypt.generate(plainText.toByteArray(), SALT.toByteArray(), cpu, memory, parallelism, outputLength);
    val stored = String(hash);
    return stored.equals(hashedStr);
}

fun main(args: Array<String>) {
  val plainText = "Hello World!";
  val stored = hashPsw(plainText);
  val isMatch = checkPsw(plainText, stored);

  println("Original: " + plainText);
  println("Hash: " + stored);
  println("Verified: " + isMatch);
}
