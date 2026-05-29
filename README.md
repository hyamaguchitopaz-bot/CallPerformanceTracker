# CallPerformanceTracker（コールセンターの成績管理アプリ）

## 概要
コールセンターでの成績を記録・管理し、簡単な分析ができるJavaアプリです。

## 作成背景
コールセンターでの経験から、
受注数や成約率の管理が重要だと感じ、
営業成績を可視化するアプリを作成しました。

## 使用技術
- Java
- Eclipse
- Git/GitHub
- ChatGPT（学習・コードレビュー補助として活用）

## 機能一覧
- データ追加
- 成績管理（条件選択あり）
- ランキング表示（条件選択あり）
- データ一覧
- CSV保存

## 実行方法
1. Eclipseでプロジェクトをインポート
2. CallRecord.java を実行
3. コンソール上で操作

## 工夫した点
- メソッド分割による可読性向上
- 条件検索用クラスを作成し、検索処理を整理
- クラスごとに役割を分け、責務を意識して設計
- ランキング機能で同率順位に対応
- ラムダ式を利用したソート処理
- レビューを受けながらコード改善を実施

## 目的
ポートフォリオとして作成しており、
Javaの基礎力向上を目的としています。

また、ChatGPTを活用しながら、
設計やコード改善について学習しています。

## 今後の改善予定
-　staticメソッドの見直し
- Stream APIを利用したコード改善
- 入力バリデーション強化
- JDBCを利用したDB管理
- Webアプリ化

---

# Overview

CallPerformanceTracker is a Java console application for managing and analyzing call center sales performance.

This application allows users to record sales results, search records, display rankings, and save data in CSV format.

# Tech Stack
- Java
- Eclipse
- Git / GitHub
- CSV File
- ChatGPT (used for learning and code review support)

# Features
- Register sales records
- Search records by conditions
- Display ranking data
- CSV data persistence
- Ranking with tie handling

# Design
- Improved readability through method separation
- Organized search processing using dedicated search classes
- Implemented object-oriented design principles
- Refactored code through iterative reviews

# Future Improvements
- Input validation enhancement
- Database integration using JDBC
- Web application development
