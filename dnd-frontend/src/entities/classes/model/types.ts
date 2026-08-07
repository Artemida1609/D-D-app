export interface DndClass {
  index: string;          
  name: string;
  hit_die: number;
  proficiency_choices: Record<string, unknown>[];
  proficiencies: Record<string, unknown>[];
  saving_throws: Record<string, unknown>[];
  starting_equipment: Record<string, unknown>[];
  class_levels: string;
  multi_classing: Record<string, unknown>;
  subclasses: Record<string, unknown>[];
  url: string;
}
