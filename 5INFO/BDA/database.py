import datetime
from random import randint
# sudo pip3 install faker
from faker import Factory

# changer uniquement ces valeurs pour modifier la génération
database_size = 1000000
customers_number = 3000
sellers_number = 700
products_number = 2400
batch_size = 25000

# initialisation du Faker pour générer plein de trucs random
fake = Factory.create('fr_FR')

# génère des vendeurs
def generate_sellers(size):
  print("Début de la génétation des vendeurs")
  sellers = []
  IDs = []
  for i in range(0, size):
    # calcul de l'ID aléatoire
    new_id = randint(1, 2000)
    # verification si l'id a deja été généré
    while (new_id in IDs):
      new_id = randint(1, 2000)
    IDs.append(new_id)
    id = new_id
    # calcul d'un nom random
    name = fake.name()
    # calcul d'une date comprise entre il y a 50 ans et aujourd'hui
    admittance_day = fake.date_time_between("-50y", "now").date()
    sellers.append([id, name, admittance_day])
  print("Fin de la génération des vendeurs")
  return sellers

# génère des clients
def generate_customers(size):
  print("Début de la génétation des clients")
  customers = []
  for i in range(0, size):
    # generation d'un ID incrémental
    id = i + 1
    # calcul d'un nom random
    name = fake.name()
    # calcul du numéro de téléphone
    phone_number = fake.phone_number()
    # calcul d'une adresse
    address = fake.street_address()
    customers.append([id, name, phone_number, address])
  print("Fin de la génétation des clients")
  return customers

# génère les produits
def generate_products(size):
  print("Début de la génétation des produits")
  products = []
  for i in range(0, size):
    # generation d'un ID incrémental
    id = i + 1
    # generation d'un nom
    name = fake.word()
    # generation d'une couleur
    color = fake.safe_color_name()
    # generation d'un description
    description = fake.sentence(nb_words=20)
    # generation d'un cout
    cost = randint(50, 250)
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
    # selection d'un produit, d'un client et d'un vendeur au hasard dans les
    # listes passées en paramètres
    product = products[randint(0, products_number-1)]
    customer = customers[randint(0, customers_number-1)]
    seller = sellers[randint(0, sellers_number-1)]
    # création d'un id incrémental
    command_id = i + 1
    # génération d'une date entre la date d'embauche du vendeur et aujourd'hui
    command_date = fake.date_time_between_dates(seller[2], datetime.datetime.now().date())
    commands.append([command_id, product[0], seller[0], customer[0], command_date])
    # creation d'un tableau avec toutes les infos nescessaires pour le remplissage
    # par paquets
    fat_entry = [product[0], product[1], product[2], product[3], product[4],
                 customer[0], customer[1], customer[2], customer[3],
                 seller[0], seller[1], seller[2], command_id, command_date]
    entries.append(fat_entry)
  attributes = ["product_id", "product_name", "product_color",
                "product_description", "product_cost", "customer_id",
                "customer_name", "customer_phone", "customer_address",
                "seller_id", "seller_name", "seller_admittance_day",
                "command_id", "command_date"]
  f.write(generate_into(table_name, attributes, entries))
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
  f.write(generate_into(table_name, attributes,  customers))

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
  f.write(generate_into(table_name, attributes, products))

  table_name = "seller"
  attributes = [
    "seller_id INT NOT NULL PRIMARY KEY",
    "seller_name VARCHAR(50)",
    "seller_admittance_day DATE"
  ]
  f.write(generate_table_header(table_name, attributes))
  attributes = ["seller_id", "seller_name", "seller_admittance_day"]
  f.write(generate_into(table_name, attributes, sellers))

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
  f.write(generate_into(table_name, attributes, commands))
  f.close()

# permet de factoriser la creation des tables
def generate_table_header(table_name, attributes):
  print("Création de l'entête de la table", table_name)
  result = "CREATE TABLE " + table_name + " (\n"
  for attribute in attributes:
    result += "    " + attribute
    if attribute != attributes[-1]:
      result += ",\n"
    else:
      result += "\n);\n"
  print(" Entête de la table", table_name, "finie")
  return result

def generate_into(table_name, attributes, values):
  print("Génération des lignes d'insertion de la table", table_name)
  result = ""
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
      # ajout de tous les attributs sauf le dernier
      for l in range(0, value_len - 1):
        attr = value[l]
        # test if the attribute is an int or something
        # put some ' around the non int value
        if isinstance(attr, int):
          batch += str(attr)
        else:
          batch += "\'" + str(attr) + "\'"
        batch += ", "
      # ajout du dernier attribut
      attr = value[value_len-1]
      if isinstance(attr, int):
        batch += str(attr)
      else:
        batch += "\'" + str(attr) + "\'"
      # si on arrive à la fin du batch,
      # ou qu'on arrive au bout du tableau de valeurs
      # on fini la requete SQL
      if k == batch_size -1 or i + k + 1 >= len(values):
        batch += ")\n;\n"
      # sinon on met une virgule pour continuer d'ajouter
      # des valeurs
      else:
        batch += "),\n"
    result += batch
  print("Lignes d'insertions", table_name, "générées")
  return result

# géneration des trucs qu'on va utiliser
sellers = generate_sellers(sellers_number)
customers = generate_customers(customers_number)
products = generate_products(products_number)
# creation et remplissage des fichiers
commands = big_N1_table(sellers, customers, products)
small_tables(sellers, customers, products, commands)
