```mermaid
graph LR;
classDef s3 fill:#dc6c11,stroke:#333,stroke-width:4px,color:#fff;
classDef lam fill:#dc6c11,stroke:#333,stroke-width:4px,color:#fff;
classDef rds fill:#3c48cc,stroke:#333,stroke-width:4px,color:#fff;
classDef ses fill:#6980e9,stroke:#333,stroke-width:4px,color:#fff;
classDef sns fill:#FF0000,stroke:#333,stroke-width:4px,color:#fff;
classDef api fill:#A4C639,stroke:#333,stroke-width:4px,color:#fff;



A01(Android App):::api --> D01[\S3 Bubket/]:::s3 ;
A01(Android App):::api -.Stored Procedure.-> E01[(RDS:Postgres)]:::rds
D01[\S3 Bubket/]:::s3 --> R01(LAMBDA):::s3
R01 -.loaded.-> G01((Rekognition Service)):::ses
E01[(RDS:Postgres)]:::rds-.Timestamp,Image ID,Coordinates.-> H01[(RDS:Postgres)]:::rds
G01((Rekognition Service)):::ses-.Extracted NP.-> H01[(RDS:Postgres)]:::rds
H01[(RDS:Postgres)]:::rds-.ben AT alerts.com.->L01((SNService)):::sns
L01((SNService)):::sns-.alerts are sent to.->J01((Insurance)):::sns
L01((SNService)):::sns-.alerts.->T01((Police)):::rds
L01((SNService)):::sns-.rewards.->B01[\Detector/]:::rds

  subgraph NP Detection Flowchart;
    titleText[A 6POINT6 Production];
  end

  subgraph A poor Boyz Production;
    titleText[A 6POINT6 Production];
  end
```
