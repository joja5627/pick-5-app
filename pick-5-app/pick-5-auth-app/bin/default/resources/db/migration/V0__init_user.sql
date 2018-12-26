-- ---------------------
-- Actions
-- ---------------------
-- Create Role 'base' (connect, create temporary table - joins, sub selects)
-- Create Role 'read' (base + read schema)
-- Create Role 'hagrid' (base + read schema + write schema)
-- ---------------------
DO $$
DECLARE 
    _database TEXT := current_database();
    _schema TEXT := 'hagrid';
    _role TEXT := 'hagrid';
BEGIN
    -- ---------------------
    -- create 'base' role with privileges: connect, create temporary table
    -- ---------------------
    IF NOT EXISTS (SELECT * FROM pg_catalog.pg_roles WHERE rolname = 'base') THEN
        CREATE ROLE base NOINHERIT;
        EXECUTE FORMAT('GRANT CONNECT, TEMPORARY ON DATABASE %I TO base', _database);
    END IF;

    -- ---------------------
    -- create 'read' role: base + read schema
    -- ---------------------
    IF NOT EXISTS (SELECT * FROM pg_catalog.pg_roles WHERE rolname = 'read') THEN
        CREATE ROLE read LOGIN INHERIT PASSWORD 'password';
        GRANT base TO read;
    END IF;

    EXECUTE FORMAT('GRANT USAGE ON SCHEMA %I TO read', _schema);
    EXECUTE FORMAT('ALTER DEFAULT PRIVILEGES IN SCHEMA %I
                    GRANT SELECT ON TABLES TO read', _schema);

    -- ---------------------
    -- create application role: base + read schema + write schema
    -- ---------------------
    IF NOT EXISTS (SELECT * FROM pg_catalog.pg_roles WHERE rolname = _role) THEN
        EXECUTE FORMAT('CREATE ROLE %I LOGIN INHERIT PASSWORD ''password''', _role);
    END IF;

    EXECUTE FORMAT('GRANT read TO %I', _role);
    EXECUTE FORMAT('GRANT USAGE ON SCHEMA %I TO %I', _schema, _role);
    EXECUTE FORMAT('ALTER DEFAULT PRIVILEGES IN SCHEMA %I
                    GRANT INSERT, UPDATE, DELETE ON TABLES TO %I', _schema, _role);
    EXECUTE FORMAT('ALTER DEFAULT PRIVILEGES IN SCHEMA %I
                    GRANT EXECUTE ON FUNCTIONS TO %I', _schema, _role);
    EXECUTE FORMAT('ALTER DEFAULT PRIVILEGES IN SCHEMA %I
                    GRANT USAGE ON SEQUENCES TO %I', _schema, _role);
END $$;
