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

## Endpoints

POST request to http://127.0.0.1:8000/user/validate_user with the following body
```

{
    'username_or_email': '<insert-username-or-email-here>',
    'password': '<insert-password-here>'
}
```
will return either a 200 status code if a user with the given username & password exists
and a 401 status code otherwise

GET request to http://127.0.0.1:8000/tasks/all will return data in the following format
```
[
    {
        'room': '<name-of-room>',
        'task': '<name-of-task>
    },
    {
        'room': '<name-of-room>',
        'task': '<name-of-task>
    },
    ...
]
```
