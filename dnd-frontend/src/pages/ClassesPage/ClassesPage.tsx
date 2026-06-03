import './ClassesPage.scss';

const classesData = [
  { name: "Barbarian", img: "/images/classes/Barbarian.png" },
  { name: "Bard", img: "/images/classes/Bard.png" },
  { name: "Cleric", img: "/images/classes/Cleric.png" },
  { name: "Druid", img: "/images/classes/Druid.png" },
  { name: "Fighter", img: "/images/classes/Fighter.png" },
  { name: "Paladin", img: "/images/classes/Paladin.png" },
  { name: "Ranger", img: "/images/classes/Ranger.png" },
  { name: "Rogue", img: "/images/classes/Rogue.png" },
  { name: "Sorcerer", img: "/images/classes/Sorcerer.png" },
  { name: "Warlock", img: "/images/classes/Warlock.png" },
  { name: "Wizard", img: "/images/classes/Wizard.png" },
];

export const ClassesPage = () => {
  return (
    <div className="classes-table-container">
      <div className="cards-fan">
        {classesData.map((cls, index) => {
          const middleIndex = Math.floor(classesData.length / 2); 
          
          const rotation = (index - middleIndex) * 7; 
          
          return (
            <div 
              key={cls.name} 
              className="card-slot"
              style={{ 
                transform: `rotate(${rotation}deg)`,
                zIndex: index 
              }}
            >
              <span className="card-title">{cls.name}</span>
              
              <img src={cls.img} alt={cls.name} className="class-card" />
            </div>
          );
        })}
      </div>
    </div>
  );
};
