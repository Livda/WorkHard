from datetime import date

begin_date = date(1901,1,1)
end_date = date(2000,12,31)

first_monday = date(1900, 1, 1)
gap = (begin_date - first_monday).days
first_day_begin_date = gap%7

print(first_day_begin_date)

nb_days = (end_date - begin_date).days - first_day_begin_date
last_week = nb_days%7

print(last_week)
