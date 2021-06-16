package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cecil")
public class Cecil extends Unit{
	public Cecil() {
		super("Cecil", "decil", "dencil", "dark cecil", "cecil dark", "dkc", "dcecil", "cecild");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8742, 8722};
				break;
			case HP:
				ids = new int[]{5080};
				break;
			case S1:
				ids = new int[]{10776, 10778};
				break;
			case S2:
				ids = new int[]{5056, 10780};
				break;
			case EX:
				ids = new int[]{8721};
				break;
			case AA:
				ids = new int[]{5041};
				break;
			case LD:
				ids = new int[]{10370};
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
			case 10777:
			case 10778:
			case 10779:
			case 10780:
				ret.fixRemoveDispels();
				break;
		}
		return ret;
	}
}