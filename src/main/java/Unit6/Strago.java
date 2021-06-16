package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Strago")
public class Strago extends Unit{
	public Strago() {
		super("Strago");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9855};
				break;
			case HP:
				ids = new int[]{9856, 9854};
				break;
			case S1:
				ids = new int[]{6388};
				break;
			case S2:
				ids = new int[]{6392};
				break;
			case EX:
				ids = new int[]{9832};
				break;
			case AA:
				ids = new int[]{9838};
				break;
			case LD:
				ids = new int[]{9834};
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
			case 9854: //Double HP++
			case 6388: //S1
			case 6392: //S2
			case 9832: //EX
			case 9834: //LD
				ret.addStaticHit("Cast this ability again if ?**" + this.getSpecificAilment(1252).getName() + "**」 is active");
				break;
		}
		return ret;
	}
}