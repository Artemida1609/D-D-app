import os
import re

directory = r'D:\DnD-app\dnd-backend\backend\src\main\java\mate\academy\jvteamproject\model\main'

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

for filename in os.listdir(directory):
    if filename.endswith('.java'):
        filepath = os.path.join(directory, filename)
        with open(filepath, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        new_lines = []
        for line in lines:
            new_lines.append(line)
            # Check if this line matches any field to translate
            for field in fields_to_translate:
                # Use regex to match with optional spaces
                escaped_field = field.replace('(', r'\(').replace(')', r'\)')
                match = re.search(r'(\s*)' + escaped_field, line)
                if match:
                    # Construct the new field name
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
                    
                    # Need to also duplicate annotations if there are any on the line above?
                    # Typically @Column or @Convert
                    # Actually, if there's @Convert on the previous line, we should duplicate it for the new field
                    
                    if len(new_lines) >= 2 and '@Convert' in new_lines[-2]:
                        # Duplicate the @Convert line
                        new_lines.append(new_lines[-2])
                    elif len(new_lines) >= 2 and '@Column' in new_lines[-2]:
                        # Duplicate the @Column line, maybe change name=""
                        col_line = new_lines[-2]
                        if 'name' in col_line:
                            col_line = col_line.replace('"', 'Ua"')
                        new_lines.append(col_line)
                    
                    new_lines.append(indent + new_field + '\n')
                    break
        
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
            
print('Done!')
