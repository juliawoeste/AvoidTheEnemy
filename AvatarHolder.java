//Julia Woeste

public class AvatarHolder {
	//holder for the avatar. 1 is vanilla, 2 is chocolate, 3 is strawberry
	private int buttonpressed;
	private int avatar2;

	public AvatarHolder() {
		avatar2 = 1;
	}

	public int getAvatar() {
		return avatar2;
	}

	public void setAvatar(int i) {
		this.avatar2 = i;

	}
}
