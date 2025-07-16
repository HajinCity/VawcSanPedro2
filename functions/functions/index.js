const functions = require("firebase-functions");
const express = require("express");
const cors = require("cors");
const crypto = require("crypto");

const app = express();
app.use(cors({ origin: true }));
app.use(express.json());

// 🔐 Define your secret key and IV (same as mobile version)
const SECRET_KEY = process.env.ENCRYPTION_SECRET_KEY || "1234567890abcdef"; // AES-128 = 16 chars
const INIT_VECTOR = process.env.ENCRYPTION_INIT_VECTOR || "abcdef1234567890"; // IV = 16 chars

app.post("/encrypt", (req, res) => {
  const { text } = req.body;

  if (!text) {
    return res.status(400).json({ error: "Missing 'text' in request body." });
  }

  try {
    const cipher = crypto.createCipheriv("aes-128-cbc", SECRET_KEY, INIT_VECTOR);
    let encrypted = cipher.update(text, "utf8", "base64");
    encrypted += cipher.final("base64");

    return res.status(200).json({ encrypted });
  } catch (err) {
    console.error("Encryption error:", err);
    return res.status(500).json({ error: "Encryption failed." });
  }
});

// 🔥 Export the function to Firebase
exports.encryptText = functions.https.onRequest(app);
