import os
import re

directory = r'D:\DnD-app\dnd-backend\backend\src\main\java\mate\academy\jvteamproject\model\main'
tables = {
    'AbilityScore': 'ability_scores',
    'Class': 'classes',
    'Condition': 'conditions',
    'DamageType': 'damage_types',
    'Equipment': 'equipments',
    'EquipmentCategory': 'equipment_categories',
    'Feature': 'features',
    'Language': 'languages',
    'Level': 'levels',
    'MagicItem': 'magic_items',
    'MagicSchool': 'magic_schools',
    'Monster': 'monsters',
    'Proficiency': 'proficiencies',
    'Race': 'races',
    'Rule': 'rules',
    'RuleSection': 'rule_sections',
    'Skill': 'skills',
    'Spell': 'spells',
    'Subclass': 'subclasses',
    'Subrace': 'subraces',
    'Trait': 'traits',
    'WeaponProperty': 'weapon_properties'
}

fields = {}

for filename in os.listdir(directory):
    if filename.endswith('.java'):
        filepath = os.path.join(directory, filename)
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        class_name = filename.replace('.java', '')
        table_name = tables.get(class_name)
        if not table_name:
            continue
            
        added_cols = []
        # find fields ending with Ua
        # example: private String nameUa; or private List<String> descriptionUa;
        matches = re.findall(r'private (?:String|List<String>) (\w+Ua);', content)
        for m in matches:
            # convert camelCase to snake_case
            col_name = re.sub(r'(?<!^)(?=[A-Z])', '_', m).lower()
            
            # get type
            if 'description' in m.lower() or 'flavor' in m.lower() or 'desc' in m.lower() or 'higherLevel' in m:
                col_type = 'LONGTEXT'
            else:
                col_type = 'VARCHAR(255)'
                
            added_cols.append((col_name, col_type))
            
        if added_cols:
            fields[table_name] = added_cols

yaml = '''databaseChangeLog:
  - changeSet:
      id: 015
      author: antigravity
      changes:
'''
for table, cols in fields.items():
    yaml += f'        - addColumn:\n            tableName: {table}\n            columns:\n'
    for col_name, col_type in cols:
        yaml += f'              - column:\n                  name: {col_name}\n                  type: {col_type}\n'

with open(r'D:\DnD-app\dnd-backend\backend\src\main\resources\db\changelog\changes\015-add-ua-translations.yaml', 'w', encoding='utf-8') as f:
    f.write(yaml)

print('Generated migration script!')
