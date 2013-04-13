/*
 * This class makes the score, health, missed items, collected items, and level display
 * on the control panel show up and updates it everytime it changes.
 *
 */

public interface ScoreDisplay
{
	void update(int score, int health, int mitems, int citems, int level);
}