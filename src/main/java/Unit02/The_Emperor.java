package Unit02;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Emperor")
public class The_Emperor extends Unit{
	public The_Emperor() { super("The Emperor"); }
	
	@Override
	public List<Ability> getAbility(AttackName type, Region region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				break;
			case HP:
				ids = Region.GL.equals(region) ? new int[]{4327, 4328, 9450} : new int[]{4327, 9333, 9451};
				break;
			case S1:
				ids = Region.GL.equals(region) ? new int[]{9323, 9324, 9450} : new int[]{15400, 15401, 9451};
				break;
			case S2:
				ids = Region.GL.equals(region) ? new int[]{9331, 9332, 9450} : new int[]{15406, 15407, 9451};
				break;
			case EX:
				ids = Region.GL.equals(region) ? new int[]{7300, 7302} : new int[]{15410, 15411};
				break;
			case AA:
				ids = Region.GL.equals(region) ? new int[]{4067} : new int[]{15418};
				break;
			case LD:
				ids = Region.GL.equals(region) ? new int[]{11591, 9450} : new int[]{13347, 9451};
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
	//BRV GL
	//HP GL
	//S1 GL 6026
	//S1T GL 6023
	//S2 GL 6040
	//S2T GL 6037 415
	//EX GL 6057
	//EXT GL 6059
	//LD GL
	
	//BRV JP
	//HP JP
	//S1 JP  11641
	//S1T JP 6029  aid 413
	//S2 JP 11647
	//S2T JP 6043
	//EX JP 16605
	//EXT JP 16607 aid 417
	//LD JP 11591
	
	//BT/BT+/BTT 11234/16610/11649
	@Override
	public void loadFixGL() {
		this.getTriggeredAbilities().clear();
	}
	@Override
	public void loadFixJP() {
		loadFixGL();
	}
}