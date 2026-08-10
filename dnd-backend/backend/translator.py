import mysql.connector
from deep_translator import GoogleTranslator
import time
import json
import logging

logging.basicConfig(level=logging.INFO, format='%(levelname)s: %(message)s')

def connect():
    return mysql.connector.connect(
        host="localhost",
        user="dnduser",
        password="1234",
        database="dnd_data",
        port=3307
    )

translator = GoogleTranslator(source='en', target='uk')

def translate_text(text):
    if not text or not isinstance(text, str):
        return text
    try:
        # Check if json array
        if text.strip().startswith('[') and text.strip().endswith(']'):
            try:
                arr = json.loads(text)
                if isinstance(arr, list) and all(isinstance(x, str) for x in arr):
                    translated_arr = [translator.translate(item) for item in arr]
                    return json.dumps(translated_arr, ensure_ascii=False)
            except:
                pass
        
        # Split text into chunks if it's too long (google translate limit is ~5000 chars)
        if len(text) > 4500:
            return translator.translate(text[:4500]) + "..."
            
        return translator.translate(text)
    except Exception as e:
        logging.error(f"Error translating: {e}")
        return text

tables_fields = {
    'ability_scores': [('name', 'name_ua'), ('description', 'description_ua')],
    'classes': [('name', 'name_ua')],
    'conditions': [('name', 'name_ua'), ('description', 'description_ua')],
    'damage_types': [('name', 'name_ua'), ('description', 'description_ua')],
    'equipments': [('name', 'name_ua'), ('description', 'description_ua')],
    'equipment_categories': [('name', 'name_ua')],
    'features': [('name', 'name_ua'), ('description_data', 'description_ua')],
    'languages': [('name', 'name_ua'), ('description_data', 'description_ua')],
    'levels': [],
    'magic_items': [('name', 'name_ua'), ('description', 'description_ua')],
    'magic_schools': [('name', 'name_ua'), ('description', 'description_ua')],
    'monsters': [('name', 'name_ua'), ('alignment', 'alignment_ua')],
    'proficiencies': [('name', 'name_ua')],
    'races': [('name', 'name_ua'), ('alignment', 'alignment_ua'), ('size_description', 'size_description_ua')],
    'rules': [('name', 'name_ua'), ('description', 'description_ua')],
    'rule_sections': [('name', 'name_ua'), ('description', 'description_ua')],
    'skills': [('name', 'name_ua'), ('description', 'description_ua')],
    'spells': [('name', 'name_ua'), ('description', 'description_ua'), ('higher_level', 'higher_level_ua')],
    'subclasses': [('name', 'name_ua'), ('description', 'description_ua'), ('subclass_flavor', 'subclass_flavor_ua')],
    'subraces': [('name', 'name_ua'), ('description', 'description_ua')],
    'traits': [('name', 'name_ua'), ('description', 'description_ua')],
    'weapon_properties': [('name', 'name_ua'), ('description', 'description_ua')]
}

conn = connect()
cursor = conn.cursor(dictionary=True)

for table, fields in tables_fields.items():
    if not fields:
        continue
        
    logging.info(f"Processing table {table}...")
    try:
        source_fields = [f[0] for f in fields]
        # get all rows where name_ua is null
        cursor.execute(f"SELECT id, {', '.join(source_fields)} FROM {table} WHERE name_ua IS NULL OR name_ua = ''")
        rows = cursor.fetchall()
        
        for row in rows:
            updates = {}
            for source_field, target_field in fields:
                if row[source_field]:
                    translated = translate_text(row[source_field])
                    updates[target_field] = translated
                    time.sleep(0.1) # rate limiting protection
            
            if updates:
                set_clause = ", ".join([f"{k} = %s" for k in updates.keys()])
                values = list(updates.values())
                values.append(row['id'])
                
                update_query = f"UPDATE {table} SET {set_clause} WHERE id = %s"
                cursor.execute(update_query, values)
                conn.commit()
                logging.info(f"Updated {table} id {row['id']}")
                
    except Exception as e:
        logging.error(f"Failed on table {table}: {e}")
        conn.rollback()

cursor.close()
conn.close()
logging.info("Translation complete!")
