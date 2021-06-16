package Unit14;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Alisaie")
public class Alisaie extends Unit{
	public Alisaie() {
		super("Alisaie", "ali");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9208};
				break;
			case HP:
				ids = new int[]{9209, 9206, 8989};
				break;
			case S1:
				ids = new int[]{8980, 8981, 8982};
				break;
			case S2:
				ids = new int[]{8986, 8987, 8988};
				break;
			case EX:
				ids = new int[]{8857};
				break;
			case AA:
				ids = new int[]{4342};
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
			case 8981: //S1+
			case 8982: //S1++
				ret.fixRemoveDispels();
				break;
			case 8987: //S2+
			case 8988: //S2++
				ret.fixRemoveDispels();
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(ret.getId()) {
			case 366:
				ret.setName("White Mana"); break;
			case 367:
				ret.setName("Black Mana"); break;
		}
		return ret;
	}
}