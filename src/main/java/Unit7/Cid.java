package Unit7;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cid")
public class Cid extends Unit{
	public Cid() {
		super("Cid");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9140}; break;
			case HP:
				ids = new int[]{9144}; break;
			case S1:
				ids = new int[]{9138}; break;
			case S2:
				ids = new int[]{9142}; break;
			case EX:
				ids = new int[]{9148}; break;
			case AA:
				ids = new int[]{7064};
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
			case 3779:
			case 9148:
				ret.fixDelayHitData(3966);
				break;
		}
		return ret;
	}
}