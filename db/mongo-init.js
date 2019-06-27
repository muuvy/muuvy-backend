db.auth('root', 'root')

db = db.getSiblingDB('muuvy')

db.createUser({
    user: 'muuvy',
    pwd: 'muuvy',
    roles: [{
        role: 'root',
        db: 'admin',
    }, ],
});
