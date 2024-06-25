import pygame
import sys
from PIL import Image

INPUT = 'Guard.png'
OUTPUT = 'Guard_Left.png'

X, Y = 0, 3 * 256//4
W, H = 256//4, 256//4
N = 4
SPACE = 0

spritesheet = Image.open(INPUT)
res = Image.new('RGBA', (W * N, H))
for i in range(N):
    left = X + i * (W + SPACE)
    upper = Y 
    right = left + W
    lower = upper + H
    sprite = spritesheet.crop((left, upper, right, lower))
    res.paste(sprite, (i * W, 0))
res.save(OUTPUT)

pygame.init()

window = pygame.display.set_mode((500, 500))
pygame.display.set_caption('Sprite Test')

def load_image(file_path):
    image = pygame.image.load(file_path).convert_alpha()
    return image

def load_sprites(file_path, rows, cols):
    sheet = load_image(file_path)
    rect = sheet.get_rect()
    width = rect.width // cols
    height = rect.height // rows
    sprites = []
    for row in range(rows):
        for col in range(cols):
            rect = pygame.Rect(col * width, row * height, width, height)
            sprite = sheet.subsurface(rect)
            sprites.append(sprite)
    return sprites, width, height

def main():
    clock = pygame.time.Clock()
    sprites, width, height = load_sprites(OUTPUT, 1, N) 
    num_sprites = len(sprites)
    c = 0
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
        c = (c + 1) % num_sprites
        x = (500 - width) // 2
        y = (500 - height) // 2
        window.fill((50, 180, 50))
        window.blit(sprites[c], (x, y))
        pygame.display.flip()
        clock.tick(8)

if __name__ == '__main__':
    main()
