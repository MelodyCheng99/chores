# Backend

## Running the Server

Run the following command:
```
pip install -r requirements.txt
```

Ensure you have MongoDB running.

Run the following command:
```
python3 manage.py runserver
```

## Accessing Admin

Run the following command, and follow the instructions to create a user (one time setup):
```
python3 manage.py createsuperuser
```

After running `python3 manage.py runserver` (no need to re-run if it is already running), navigate to http://127.0.0.1:8000/admin & log in with your created user's username & password.