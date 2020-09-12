package UnitOthers;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.LannReynn")
public class LannReynn extends Unit{
	public LannReynn() {
		super("Lann&Reynn", "lann & reynn", "lann & reinn", "lann", "reynn", "reinn", "lann&reinn", "twins", "l&r", "lr");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6840};
				break;
			case HP:
				ids = new int[]{6844};
				break;
			case S1:
				ids = new int[]{6834, 6835};
				break;
			case S2:
				ids = new int[]{6838, 6839};
				break;
			case EX:
				ids = new int[]{6843};
				break;
			case AA:
				ids = new int[]{6831};
				break;
			case LD:
				ids = new int[]{};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 6835:
				if(!ret.getName().contains("+"))
					ret.setName(ret.getName() + "+");
			case 6839:
				ret.addStaticHit("Enabled when both 「**" + super.getSpecificAilment(902).getName() + "**」 and 「**" + super.getSpecificAilment(905).getName() + "**」 are active", 0);
				break;
			case 6843:
				ret.removeAilmentById(161);
				ret.removeHitDataById(4506);
		}
		return ret;
	}
}