# This script use Python3, install it !
import datetime
from random import randint
# It need an external lib, so you have to download it
# with the following command line :
# sudo pip3 install faker
from faker import Factory

# ONLY change this values to modify the generation
database_size = 1000000
customers_number = 3000
sellers_number = 700
products_number = 2400
batch_size = 25000

# Faker initialisation, which help us for pretty random generation
fake = Factory.create('fr_FR')

# generate all the sellers
def generate_sellers(size):
  print("Début de la génétation des vendeurs")
  sellers = []
  IDs = []
  for i in range(0, size):
    # calculation for a random ID
    new_id = randint(1, 2000)
    # check if the ID is already generated
    while (new_id in IDs):
      new_id = randint(1, 2000)
    IDs.append(new_id)
    id = new_id
    # new random name
    name = fake.name()
    # new random date between 50 years ago and today
    admittance_day = fake.date_time_between("-50y", "now").date()
    # add the seller in the array
    sellers.append([id, name, admittance_day])
  print("Fin de la génération des vendeurs")
  return sellers

# generate all the customers
def generate_customers(size):
  print("Début de la génétation des clients")
  customers = []
  for i in range(0, size):
    # create an incremental ID
    id = i + 1
    # new random name
    name = fake.name()
    # new random phone number
    phone_number = fake.phone_number()
    # new random address
    address = fake.street_address()
    # add the custosmer to the array
    customers.append([id, name, phone_number, address])
  print("Fin de la génétation des clients")
  return customers

# generate all the products
def generate_products(size):
  print("Début de la génétation des produits")
  products = []
  for i in range(0, size):
    # create an incremental ID
    id = i + 1
    # new random name
    name = fake.word()
    # new random color
    color = fake.safe_color_name()
    # new random words as description
    description = fake.sentence(nb_words=20)
    # new random int between 50 and 250
    cost = randint(50, 250)
    # add the product to the array
    products.append([id, name, color, description, cost])
  print("Fin de la génétation des produits")
  return products

def big_N1_table(sellers, customers, products):
  table_name = "fat_table"
  f = open(table_name + '.sql', 'w')
  attributes = [
    "product_id INT NOT NULL",
    "product_name VARCHAR(100)",
    "product_color VARCHAR(10)",
    "product_description TEXT",
    "product_cost INT",
    "customer_id INT NOT NULL",
    "customer_name VARCHAR(40)",
    "customer_phone VARCHAR(30)",
    "customer_address VARCHAR(255)",
    "seller_id INT NOT NULL",
    "seller_name VARCHAR(40)",
    "seller_admittance_day DATE",
    "command_id INT NOT NULL PRIMARY KEY",
    "command_date DATE"
  ]
  f.write(generate_table_header(table_name, attributes))
  commands = []
  entries = []
  for i in range(0, database_size):
    # select a product, a customer and a seller randomly in the parameters list
    product = products[randint(0, products_number-1)]
    customer = customers[randint(0, customers_number-1)]
    seller = sellers[randint(0, sellers_number-1)]
    print("Selection des infos pour la ligne", i)
    # create an incremental ID
    command_id = i + 1
    # generate a date between the admition day of the seller and today
    command_date = fake.date_time_between_dates(seller[2], datetime.datetime.now().date())
    commands.append([command_id, product[0], seller[0], customer[0], command_date])
    # create an array with all the data required for the batch filling
    fat_entry = [product[0], product[1], product[2], product[3], product[4],
                 customer[0], customer[1], customer[2], customer[3],
                 seller[0], seller[1], seller[2], command_id, command_date]
    entries.append(fat_entry)
  attributes = ["product_id", "product_name", "product_color",
                "product_description", "product_cost", "customer_id",
                "customer_name", "customer_phone", "customer_address",
                "seller_id", "seller_name", "seller_admittance_day",
                "command_id", "command_date"]
  generate_into(table_name, attributes, entries, f)
  f.close()
  return commands

def small_tables(sellers, customers, products, commands):
  f = open('small_tables.sql', 'w')
  table_name = "customer"
  attributes = [
    "customer_id INT NOT NULL PRIMARY KEY",
    "customer_name VARCHAR(50)",
    "customer_phone VARCHAR(30)",
    "customer_address VARCHAR(255)"
  ]
  f.write(generate_table_header(table_name, attributes))
  attributes = ["customer_id", "customer_name", "customer_phone",
                "customer_address"]
  generate_into(table_name, attributes,  customers, f)

  table_name = "product"
  attributes = [
    "product_id INT NOT NULL PRIMARY KEY",
    "product_name VARCHAR(100)",
    "product_color VARCHAR(10)",
    "product_description TEXT",
    "product_cost INT"
  ]
  f.write(generate_table_header(table_name, attributes))
  attributes = ["product_id", "product_name", "product_color",
                "product_description", "product_cost"]
  generate_into(table_name, attributes, products, f)

  table_name = "seller"
  attributes = [
    "seller_id INT NOT NULL PRIMARY KEY",
    "seller_name VARCHAR(50)",
    "seller_admittance_day DATE"
  ]
  f.write(generate_table_header(table_name, attributes))
  attributes = ["seller_id", "seller_name", "seller_admittance_day"]
  generate_into(table_name, attributes, sellers, f)

  table_name = "command"
  attributes = [
    "command_id INT NOT NULL PRIMARY KEY",
    "product_id INT REFERENCES Product(product_id)",
    "seller_id INT REFERENCES Seller(seller_id)",
    "customer_id INT REFERENCES Customer(customer_id)",
    "command_date DATE"
  ]
  f.write(generate_table_header(table_name, attributes))
  attributes = ["command_id", "product_id", "seller_id", "customer_id", "command_date"]
  generate_into(table_name, attributes, commands, f)
  f.close()

# generate the "CREATE TABLE" instruction
def generate_table_header(table_name, attributes):
  print("Création de l'entête de la table", table_name)
  result = "CREATE TABLE " + table_name + " (\n"
  for attribute in attributes:
    result += "    " + attribute
    if attribute != attributes[-1]:
      result += ",\n"
    else:
      result += "\n);\n"
  print("Entête de la table", table_name, "finie")
  return result

# generete the "INSERT INTO" instructions by batch
def generate_into(table_name, attributes, values, file):
  print("Génération des lignes d'insertion de la table", table_name)
  for i in range(0, len(values), batch_size):
    # generation of the first line in which the attribute names and their order
    # is defined
    batch = "INSERT INTO " + table_name +" ("
    attributes_len = len(attributes)
    for j in range(0, len(attributes)-1):
      attribute = attributes[j]
      batch += attribute
      batch += ", "
    batch += attributes[attributes_len-1] + ")\n"
    batch += "VALUES\n"
    # insertion of all the values ordered as we define before
    for k in range(0, batch_size):
      if i+k >= len(values):
        break
      # add spaces for nice indentation
      batch += "    ("
      value = values[i+k]
      value_len = len(value)
      # add all the attributes but not the last
      for l in range(0, value_len - 1):
        attr = value[l]
        # test if the attribute is an int or something
        # put some ' around the non int value
        if isinstance(attr, int):
          batch += str(attr)
        else:
          batch += "\'" + str(attr) + "\'"
        batch += ", "
      # add the last attribute
      attr = value[value_len-1]
      if isinstance(attr, int):
        batch += str(attr)
      else:
        batch += "\'" + str(attr) + "\'"
      # if it's the end of the batch or the end of the array
      # the SQL request is ended
      if k == batch_size -1 or i + k + 1 >= len(values):
        batch += ")\n;\n"
      # otherwise we put a comma to continue the insertions
      else:
        batch += "),\n"
    file.write(batch)
    print("Ajout d'un batch dans ", table_name)
  print("Lignes d'insertions", table_name, "générées")
  return 1

# generate stuff we're gonna to use
sellers = generate_sellers(sellers_number)
customers = generate_customers(customers_number)
products = generate_products(products_number)
# creation and filling the files
commands = big_N1_table(sellers, customers, products)
small_tables(sellers, customers, products, commands)
