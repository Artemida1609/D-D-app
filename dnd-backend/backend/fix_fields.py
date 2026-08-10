import os
import re

directory = r'D:\DnD-app\dnd-backend\backend\src\main\java\mate\academy\jvteamproject\model\main'

for filename in os.listdir(directory):
    if filename.endswith('.java'):
        filepath = os.path.join(directory, filename)
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Fix the Ua"..." errors
        content = re.sub(r'Ua"([^"]*)"', r'"\1Ua"', content)
        content = content.replace('"LONGTEXTUa"', '"LONGTEXT"')
        content = content.replace('"TEXTUa"', '"TEXT"')
        
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)

print('Fixed Ua" issues!')
