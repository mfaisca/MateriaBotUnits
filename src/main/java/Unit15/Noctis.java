package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Noctis")
public class Noctis extends Unit{
	public Noctis() {
		super("Noctis");
	}
	
	@Override
	public List<Ability> getAbility(AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				break;
			case HP:
				ids = region.equals("GL") ? new int[]{4327, 4328, 9450} : new int[]{4327, 9333, 9451};
				break;
			case S1:
				ids = region.equals("GL") ? new int[]{9323, 9324, 9450} : new int[]{15400, 15401, 9451};
				break;
			case S2:
				ids = region.equals("GL") ? new int[]{9331, 9332, 9450} : new int[]{15406, 15407, 9451};
				break;
			case EX:
				ids = region.equals("GL") ? new int[]{7300, 7302} : new int[]{15410, 15411};
				break;
			case AA:
				ids = region.equals("GL") ? new int[]{4067} : new int[]{15418};
				break;
			case LD:
				ids = region.equals("GL") ? new int[]{13347, 9450} : new int[]{13347, 9451};
				break;
			case BT:
				ids = new int[]{9216, 15415};
				break;
			case CA:
				break;
			case CALD:
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
	public Ability getSpecificAbility(Integer id) {
		Ability a = super.getSpecificAbility(id);
		if(a != null)
			switch(a.getId()) {
			case 4560:
				a.setName(super.getSpecificAbility(9450).getName());
				break;
			}
		return a;
	}
	@Override
	public Passive getSpecificPassive(Integer id) {
		return super.getSpecificPassive(id);
	}
	@Override
	public Ailment getSpecificAilment(Integer id) {
		return super.getSpecificAilment(id);
	}
}