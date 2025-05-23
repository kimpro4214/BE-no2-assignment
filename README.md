#일정 관리 API 설계
![일정 관리 API 설계](https://github.com/user-attachments/assets/4026784b-a7df-40b1-9aa2-139e919b987d)



#상세 API 설계
### 1. 일정 등록

- **Method**: `POST`
- **URL**: `/api/schedules`
- **Request Body**

json
{
  "title": "스터디 준비",
  "writer": "철수",
  "password": "1234"
}
- **Response Body**

json
{
  "id": 1,
  "title": "스터디 준비",
  "writer": "철수",
  "createdAt": "2025-05-20T10:00:00",
  "modifiedAt": "2025-05-20T10:00:00"
}
- **Status**: `201 Created`

### 2. 일정 단건 조회

- **Method**: `GET`
- **URL**: `/api/schedules/{id}`
- **Response Body**

json
{
  "id": 1,
  "title": "스터디 준비",
  "writer": "철수",
  "createdAt": "2025-05-20T10:00:00",
  "modifiedAt": "2025-05-20T11:00:00"
}
- **Status**: `200 OK`

### 3. 전체 일정 조회

- **Method**: `GET`
- **URL**: `/api/schedules?modifiedAt=2025-05-20&writer=철수` (쿼리 파라미터는 선택)
- **Response Body**

json
[
  {
    "id": 1,
    "title": "스터디 준비",
    "writer": "철수",
    "createdAt": "2025-05-20T10:00:00",
    "modifiedAt": "2025-05-20T11:00:00"
  },
  ...
]
- **Status**: `200 OK`

### 4. 일정 수정

- **Method**: `PUT`
- **URL**: `/api/schedules/{id}`
- **Request Body**

json
{
  "title": "발표 준비",
  "writer": "철수",
  "password": "1234"
}
- **Response Body**

json
{
  "id": 1,
  "title": "발표 준비",
  "writer": "철수",
  "createdAt": "2025-05-20T10:00:00",
  "modifiedAt": "2025-05-20T12:00:00"
}
- **Status**: `200 OK`

- **실패 시**

json
{
  "error": "비밀번호가 일치하지 않습니다."
}

### 5. 일정 삭제

- **Method**: `DELETE`
- **URL**: `/api/schedules/{id}`
- **Request Body**

json
{
  "password": "1234"
}
- **Response Body**

json
{
  "message": "일정이 성공적으로 삭제되었습니다."
}

- **실패 시**

json
{
  "error": "비밀번호가 일치하지 않습니다."
}
- **Status**: `200 OK`

  #ERD
  ![Untitled](https://github.com/user-attachments/assets/83a008a9-4fe3-4c83-815c-8caf5cab61c0)

