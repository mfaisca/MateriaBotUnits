package Unit01;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Enumerators.Ability.HitData.Type;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Warrior_of_Light")
public class Warrior_of_Light extends Unit{
	public Warrior_of_Light() { super("Warrior of Light"); }
	
	@Override
	public void loadFixGL() {
		this.getSpecificAilment(2745).getAuras().stream().filter(a -> a.getId() == 1237).forEach(a -> a.getRankData()[0] = 330);
		this.getSpecificAilment(2797).getAuras().stream().filter(a -> a.getId() == 1273).forEach(a -> a.getRankData()[0] = 330);
	}
	
	@Override
	public void loadFixJP() {
		loadFixGL();
		this.getSpecificAbility(17137).getHitData().stream().filter(hd -> hd.getType().equals(Type.BRV)).forEach(hd -> {
			hd.setSingleTargetBrvRate(240);
			hd.setBrvDamageLimitUp(200);
			hd.setMaxBrvLimitUp(400);
		});
	}
}