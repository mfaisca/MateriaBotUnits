package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ardyn")
public class Ardyn extends Unit{
	public Ardyn() {
		super("Ardyn", "izunia", "adagium", "zannen");
	}
	
	@Override
	public List<Ability> getAbility(AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9535};
				break;
			case HP:
				ids = new int[]{9533, 9537};
				break;
			case S1:
				ids = new int[]{9172, 9537};
				break;
			case S2:
				ids = new int[]{9176, 9537};
				break;
			case EX:
				ids = new int[]{9191, 9537};
				break;
			case AA:
				ids = new int[]{9199};
				break;
			case LD:
				ids = new int[]{9194, 9537};
				break;
			case BT:
				ids = new int[]{9195};
				break;
			case CA:
				ids = new int[]{};
				break;
			case CALD:
				ids = new int[]{};
				break;
			default:
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(Integer id){
		Ability ret = super.getSpecificAbility(id);
		return ret;
	}
	
	@Override
	public Passive getSpecificPassive(Integer id) {
		Passive ret = super.getSpecificPassive(id);
		return ret;
	}
}