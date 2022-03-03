# API

| エンドポイント                                       | Method | 役割                             | 
| ---------------------------------------------------- | ------ | -------------------------------- | 
| /articles                                            | GET    | 全ての記事の取得                 | 
| /articles/{id}                                       | GET    | 特定の記事の取得                 | 
| /articles                                            | POST   | 記事を作成                       | 
| /articles                                            | PUT    | 記事を更新                       | 
| /articles                                            | DELETE | 記事を削除                       | 
| /articles/{article_id}/comments                      | GET    | 特定の記事のコメントと返信を取得 | 
| /articles/{article_id}/comments                      | POST   | 特定の記事のコメントを作成       | 
| /articles/{article_id}/comments/{comment_id}/replies | POST   | 特定のコメントの返信を作成       | 

https://notepm.jp/markdown-table-tool
