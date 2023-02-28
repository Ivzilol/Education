package magicGame.models.magics;

public class BlackMagic extends MagicImpl{

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    int bullets = this.getBulletsCount();

    @Override
    public int fire() {
        if (bullets - 10 >= 10) {
            bullets -= 10;
            return 10;
        } else {
            bullets = 0;
            return 0;
        }
    }
}
