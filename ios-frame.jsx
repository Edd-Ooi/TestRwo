import React from "react";

export default function IOSDevice({ children, title = "Habit Tracker Preview" }) {
  return (
    <section aria-label={title} style={{ maxWidth: 430, margin: "0 auto", padding: 10, borderRadius: 36, background: "#111", boxShadow: "0 12px 34px rgba(0,0,0,0.22)" }}>
      <div style={{ height: 28, display: "flex", justifyContent: "center", alignItems: "center", color: "#cfcfcf", fontFamily: "Nunito, sans-serif", fontSize: 12, fontWeight: 700 }}>● ● ●</div>
      <div style={{ borderRadius: 26, overflow: "hidden", background: "#fff", minHeight: 780 }}>{children}</div>
    </section>
  );
}
