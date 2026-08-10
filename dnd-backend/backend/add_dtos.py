import os
import re

directory = r'D:\DnD-app\dnd-backend\backend\src\main\java\mate\academy\jvteamproject\dto'

fields_to_translate = [
    r'private String name;',
    r'private String description;',
    r'private List<String> description;',
    r'private String desc;',
    r'private List<String> desc;',
    r'private String alignment;',
    r'private String sizeDescription;',
    r'private String subclassFlavor;',
    r'private List<String> higherLevel;'
]

for root, dirs, files in os.walk(directory):
    for filename in files:
        if filename.endswith('.java'):
            filepath = os.path.join(root, filename)
            with open(filepath, 'r', encoding='utf-8') as f:
                lines = f.readlines()
            
            new_lines = []
            for line in lines:
                new_lines.append(line)
                for field in fields_to_translate:
                    escaped_field = field.replace('(', r'\(').replace(')', r'\)')
                    match = re.search(r'(\s*)' + escaped_field, line)
                    if match:
                        indent = match.group(1)
                        if 'List<String>' in field:
                            if 'description' in field:
                                new_field = 'private List<String> descriptionUa;'
                            elif 'desc' in field:
                                new_field = 'private List<String> descUa;'
                            elif 'higherLevel' in field:
                                new_field = 'private List<String> higherLevelUa;'
                        else:
                            if 'name' in field:
                                new_field = 'private String nameUa;'
                            elif 'description' in field:
                                new_field = 'private String descriptionUa;'
                            elif 'desc' in field:
                                new_field = 'private String descUa;'
                            elif 'alignment' in field:
                                new_field = 'private String alignmentUa;'
                            elif 'sizeDescription' in field:
                                new_field = 'private String sizeDescriptionUa;'
                            elif 'subclassFlavor' in field:
                                new_field = 'private String subclassFlavorUa;'
                        
                        new_lines.append(indent + new_field + '\n')
                        break
            
            with open(filepath, 'w', encoding='utf-8') as f:
                f.writelines(new_lines)
            
print('Done DTOs!')
