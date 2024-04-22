# API 명세

## 1, 2, 3 단계
### 메인 페이지 조회

#### Request

```http request
GET /admin HTTP/1.1
```

#### Response

```
HTTP/1.1 200 
```

### 예약 페이지 조회

#### Request

```http request
GET /admin/reservation HTTP/1.1
```

#### Response

```
HTTP/1.1 200 
```

### 예약 조회

#### Request

```http request
GET /reservations HTTP/1.1
```

#### Response

```
HTTP/1.1 200 
Content-Type: application/json

[
    {
        "id": 1,
        "name": "브라운",
        "date": "2023-01-01",
        "time": "10:00"
    },
    {
        "id": 2,
        "name": "브라운",
        "date": "2023-01-02",
        "time": "11:00"
    }
]
```

### 예약 추가

#### Request

```http request
POST /reservations HTTP/1.1
content-type: application/json

{
"date": "2023-08-05",
"name": "브라운",
"time": "15:40"
}
```

#### Response

```
HTTP/1.1 201
Location: /reservation/{id}
```

### 예약 취소

#### Request

```http request
DELETE /reservations/{id} HTTP/1.1
```

#### Response

```
HTTP/1.1 204
```