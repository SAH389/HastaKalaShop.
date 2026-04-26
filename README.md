# 🛍️ Hasta-Kala Shop
### Micro-Sales Analytics App for Small Artisans

![Android](https://img.shields.io/badge/Platform-Android-green)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple)
![Room](https://img.shields.io/badge/Database-Room-blue)

---

## 👩‍💻 Developer Details

| Field | Details |
|-------|---------|
| **Name** | Sahana A C |
| **USN** | 1HK22CS125 |
| **Email** | 1hk22cs125@hkbk.edu.in |
| **Internship** | Android App Development using GenAI |
| **Company** | MindMatrix |
| **Project No** | 14 |

---

## 📱 About the App

Hasta-Kala Shop is a **Micro-Sales Analytics** Android app designed for small-scale artisans like banana fiber bag makers, keychain artisans, and handloom weavers.

### 🎯 Problem Solved
Small artisans don't know which product/color sells the most. They keep making everything without data — resulting in **"Dead Stock"** and lost income.

### 💡 Solution
Every time they sell something, they log it in 3 taps. The app shows them:
- 📊 **Which product sells the most** (Pie Chart)
- 🎨 **Which color is most popular** (Bar Chart)
- ⚠️ **Stock alerts** when items run low
- 💰 **Daily/Weekly/Monthly income**

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| ➕ Quick Bill | Log a sale in under 5 seconds |
| 📊 Best Seller Dashboard | Pie & Bar charts showing top products |
| ⚠️ Stock Alert | Warning when stock runs low |
| 📋 Income Log | Filter sales by Today/Week/Month |
| 💰 Earnings Tracker | Real-time total earnings |
| 📱 Offline First | Works without internet |

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Kotlin** | Programming Language |
| **MVVM Architecture** | Clean code structure |
| **Room Database** | Local data storage |
| **LiveData** | Real-time UI updates |
| **MPAndroidChart** | Pie & Bar charts |
| **ViewBinding** | View access |
| **Coroutines** | Async operations |
| **RecyclerView** | Sales list display |

---

## 📁 Project Structure

com.sahana.hastakala
├── data/
│   ├── SaleEntity.kt
│   ├── SaleDao.kt
│   └── AppDatabase.kt
├── repository/
│   └── SaleRepository.kt
├── viewmodel/
│   └── SaleViewModel.kt
├── ui/
│   ├── MainActivity.kt
│   ├── NewSaleActivity.kt
│   ├── DashboardActivity.kt
│   └── IncomeLogActivity.kt
└── utils/
└── DateUtils.kt

---

## 🚀 How to Run

1. Clone the repository
```bash
git clone https://github.com/SAH389/HastaKalaShop.git
```
2. Open in **Android Studio**
3. Wait for Gradle sync
4. Click **▶ Run**

## 📱 App Screenshots

<table>
  <tr>
    <td align="center">
      <img src="home_screen.png" width="200"/>
      <br/>
      <b>🏠 Home Screen</b>
    </td>
    <td align="center">
      <img src="new_sale.png" width="200"/>
      <br/>
      <b>➕ New Sale</b>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="dashboard.png" width="200"/>
      <br/>
      <b>📊 Dashboard</b>
    </td>
    <td align="center">
      <img src="income_log.png" width="200"/>
      <br/>
      <b>📋 Income Log</b>
    </td>
  </tr>
</table>

---

## 🎥 Demo Video

[![Demo Video](https://img.youtube.com/vi/YOUR_VIDEO_ID/0.jpg)](https://youtube.com/shorts/q3kuYCvHWIQ?feature=share)

> 📺 Click the image above to watch the full demo video


## 🎯 Impact Goals

- 💼 **Business Intelligence** — Data power for micro entrepreneurs
- ♻️ **Waste Reduction** — Stop overproducing unsold items
- 📈 **Growth Mindset** — Track business progress weekly

---

## 📄 License
This project was built as part of the **MindMatrix VTU Internship Program 2026**

---

*Built with ❤️ by Sahana A C | MindMatrix VTU Internship 2026*
