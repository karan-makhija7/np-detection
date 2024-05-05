import psycopg2, datetime, time

# Connect to the PostgreSQL database
conn = psycopg2.connect(
    host="",
    database="",
    port=,
    user="",
    password=""
)

# Create a cursor object to execute SQL queries
cursor = conn.cursor()

table1 = "incoming"
table2 = "stolen"

# Execute queries to delete the tables
delete_query1 = f"DROP TABLE IF EXISTS {table1}"
delete_query2 = f"DROP TABLE IF EXISTS {table2}"

cursor.execute(delete_query1)
cursor.execute(delete_query2)

# Define the SQL query to create a table
create_table1_query = '''
    CREATE TABLE IF NOT EXISTS incoming(
        number VARCHAR(255) PRIMARY KEY,
        time TIMESTAMP,
        coordinates POINT
    )
'''
create_table2_query = '''
    CREATE TABLE IF NOT EXISTS stolen(
        number VARCHAR(255) PRIMARY KEY
    )
'''
# Execute the create table query
cursor.execute(create_table1_query)
cursor.execute(create_table2_query)

current_timestamp = datetime.datetime.now()
# Construct the INSERT statement
insert_query = "INSERT INTO incoming (number, time, coordinates) VALUES (%s, %s, %s)"
data = [("1", current_timestamp, "(100,100)"),("2", current_timestamp, "(99,99)"),("3", current_timestamp, "(98,98)"),("4", current_timestamp, "(97,97)"),]

for x in data:
    cursor.execute(insert_query, x)

insert_query2 = "INSERT INTO stolen (number) VALUES (%s)"
data2 = ["1","2","5","7"]
for x in data2:
    cursor.execute(insert_query2, x)


# Create a cursor object to interact with the database
cursor = conn.cursor()

# Specify the names of the two tables you want to compare
table1 = "stolen"
table2 = "incoming"


# code to execute the query in a loop
t = 10
while (False):
    # query
    # wait
    time.sleep(t)
    pass


# Execute a query to find the common primary keys between the tables
query = f"""
    SELECT t1.number
    FROM {table1} t1
    INNER JOIN {table2} t2 ON t1.number = t2.number
"""
cursor.execute(query)



# Fetch the common primary keys (if any)
common_keys = cursor.fetchall()

# Print the common primary keys (if any)
if len(common_keys) == 0:
    print("No stolen cars found")
else:
    print("Stolen cars found:")
    for row in common_keys:
        print(row[0])

# Commit the changes and close the cursor and connection
conn.commit()
cursor.close()
conn.close()



