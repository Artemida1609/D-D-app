import os
import re

directory = r'D:\DnD-app\dnd-backend\backend\src\main\java\mate\academy\jvteamproject\service\impl\main'

for filename in os.listdir(directory):
    if filename.endswith('.java'):
        filepath = os.path.join(directory, filename)
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Replace e.getName(), with e.getName(),\n                        e.getNameUa(),
        if 'e.getName(),' in content:
            content = content.replace('e.getName(),', 'e.getName(),\n                        e.getNameUa(),')
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)

print('Fixed services!')
