export const ItemTypes = [
    "MATERIAL",
    "EQUIPMENT"
] as const;

export type ItemType = typeof ItemTypes[number];

export const ItemRarities = [
    "COMMON",
    "UNCOMMON",
    "RARE",
    "EPIC",
    "UNIQUE",
    "LEGENDARY"
] as const;

export type ItemRarity = typeof ItemRarities[number];