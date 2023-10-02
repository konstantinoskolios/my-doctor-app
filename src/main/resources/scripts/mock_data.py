import faker
import psycopg2
import random

# Database connection details
db_config = {
    'host': 'localhost',
    'database': 'postgres',
    'user': 'pgtest',
    'password': 'pgtest',
}

# Connect to the database
conn = psycopg2.connect(**db_config)
cursor = conn.cursor()

# Create a Faker instance for generating random data
fake = faker.Faker()

# Generate and insert 5000 random records
for _ in range(5000):
    first_name = fake.first_name()
    last_name = fake.last_name()
    father_first_name = fake.first_name_male()
    tax_number = ''.join(random.choices('0123456789', k=9))
    social_security_number = ''.join(random.choices('0123456789', k=11))
    phone_number = '69' + ''.join(
        random.choices('0123456789', k=8))  # Make sure it starts with '69' and has 10 digits in total
    birthdate = fake.date_of_birth(minimum_age=18, maximum_age=70).strftime('%Y-%m-%d')

    insert_query = """
    INSERT INTO citizen (first_name, last_name, father_first_name, tax_number, social_security_number, phone_number, birthdate)
    VALUES (%s, %s, %s, %s, %s, %s, %s)
    """
    cursor.execute(insert_query, (
    first_name, last_name, father_first_name, tax_number, social_security_number, phone_number, birthdate))

# Commit changes and close the connection
conn.commit()
conn.close()
