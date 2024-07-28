<h2>Flower Mimics</h2>
<p><a href="https://github.com/Serilum/Flower-Mimics"><img src="https://serilum.com/assets/data/logo/flower-mimics.gif"></a></p><h2>Download</h2>
<p>You can download Flower Mimics on CurseForge and Modrinth:</p><p>&nbsp;&nbsp;CurseForge: &nbsp;&nbsp;<a href="https://curseforge.com/minecraft/mc-mods/flower-mimics">https://curseforge.com/minecraft/mc-mods/flower-mimics</a><br>&nbsp;&nbsp;Modrinth: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://modrinth.com/mod/flower-mimics">https://modrinth.com/mod/flower-mimics</a></p>
<h2>Issue Tracker</h2>
<p>To keep a better overview of all mods, the issue tracker is located in a separate repository.<br>&nbsp;&nbsp;For issues, ideas, suggestions or anything else, please follow this link:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/issue-tracker">Issue Tracker</a></p>
<h2>Pull Requests</h2>
<p>Because of the way mod loader files are bundled into one jar, some extra information is needed to do a PR.<br>&nbsp;&nbsp;A wiki page entry about it is available here:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/pull-requests">Pull Request Information</a></p>
<h2>Mod Description</h2>
<p style="text-align:center"><a href="https://serilum.com/" target="_blank" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/header/header.png" alt="" width="838" height="400"></a></p>
<p style="text-align:center"><a href="https://curseforge.com/members/serilum/projects" target="_blank" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/curseforge.svg" width="200"></a> <a href="https://modrinth.com/user/Serilum" target="_blank" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/modrinth.svg" width="200"></a> <a href="https://patreon.com/serilum" target="_blank" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/patreon.svg" width="200"></a> <a href="https://youtube.com/@serilum" target="_blank" rel="nofollow"><img src="https://raw.githubusercontent.com/Serilum/.data-workflow/main/badges/svg/youtube.svg" width="200"></a></p>
<p><strong><span style="font-size:24px">Requires the library mod&nbsp;<a style="font-size:24px" href="https://curseforge.com/minecraft/mc-mods/collective" target="_blank" rel="nofollow">Collective</a>.</span></strong><br><br><strong>&nbsp;&nbsp;&nbsp;This mod is part of <span style="color:#008000"><a style="color:#008000" href="https://curseforge.com/minecraft/modpacks/the-vanilla-experience" target="_blank" rel="nofollow">The Vanilla Experience</a></span> modpack and <span style="color:#006b3f"><a style="color:#006b3f" href="https://curseforge.com/minecraft/mc-mods/serilums-rpg-bundle" target="_blank" rel="nofollow">Serilum's RPG Bundle</a></span> mod.</strong><br><span style="font-size:18px">Flower Mimics is a mod made for SpookyJam 2023. It's highly configurable. By default, whenever a player walks around at night, any flower can be a mimic and turn into a mob. When this happens, lightning will strike. The mimic will have a custom name. When the player kills the mimics, extra items and experience are dropped. The flower from which it transforms, will be placed back in the original location. There is a <strong>1/50</strong> chance that a flower is a mimic.<br><br><strong>Can cause some jump scares! <span style="font-size:18px">👻</span></strong></span><span style="font-size:18px"><br><br>While there are default values for an instant experience, it's possible to change a lot of behaviour. Which flowers can become mimics, what items are dropped and various settings when and how mimics should appear.</span><br><br><br><span style="font-size:18px"><strong>Be careful when walking around at night! A flower might just be something else:</strong></span></p>
<div class="spoiler">
<p><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/flower-mimics/c.gif"></picture></p>
</div>
<p>&nbsp;</p>
<p><br><span style="font-size:18px"><strong>By default, whenever you kill a mimic it'll place the original flower back. Extra items and experience are dropped by the mimic:</strong></span></p>
<div class="spoiler">
<p><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/flower-mimics/d.gif"></picture></p>
</div>
<p>&nbsp;<br><br><strong><span style="font-size:20px">Configurable:</span> <span style="color:#008000;font-size:14px"><a style="color:#008000" href="https://github.com/Serilum/.information/wiki/how-to-configure-mods" rel="nofollow">(&nbsp;how do I configure?&nbsp;)</a></span><br></strong><span style="font-size:12px"><strong>flowerIsMimicChance</strong>&nbsp;(default = 0.02, min 0, max 1.0): The chance an encountered flower is a mimic, and transforms into a mob.</span><br><span style="font-size:12px"><strong>extraExperienceOnMimicDeath</strong>&nbsp;(default = 30): The amount of extra experience that should drop whenever a mimic is killed.</span><br><span style="font-size:12px"><strong>dropExtraItemsOnMimicDeath</strong>&nbsp;(default = true): Whether the items specified in ./config/flowermimics/.. should be dropped whenever a mimic is killed.</span><br><span style="font-size:12px"><strong>onlyTransformMimicsAtNight</strong>&nbsp;(default = true): Whether flowers should only transform into mobs when it is night time. When disabled, they can always transform.</span><br><span style="font-size:12px"><strong>resetMimicsBeforeNightTime</strong>&nbsp;(default = true): Whether mimic flower possibilities should be reset when the day turns into night.</span><br><span style="font-size:12px"><strong>removeFlowerBlockOnMimicSpawn</strong>&nbsp;(default = true): Whether the flower block from which the mimic transforms should be removed on spawn.</span><br><span style="font-size:12px"><strong>dropFlowerItemOnMimicDeath</strong>&nbsp;(default = false): If the mimic flower should be dropped on death. Must have 'removeFlowerBlockOnMimicSpawn' enabled.</span><br><span style="font-size:12px"><strong>replaceOriginalFlowerBlockOnMimicDeath</strong>&nbsp;(default = true): If the original flower block should be replaced when a mimic dies. Must have 'removeFlowerBlockOnMimicSpawn' enabled. Destination must have an air block.</span><br><span style="font-size:12px"><strong>placeFlowerBlockWhereMimicDies</strong>&nbsp;(default = false): If the a flower block should be placed where a mimic dies. Must have 'removeFlowerBlockOnMimicSpawn' enabled. Destination must have an air block.</span><br><span style="font-size:12px"><strong>showLightningOnMimicSpawn</strong>&nbsp;(default = true): If lightning should be shown whenever a mimic spawns.</span><br><span style="font-size:12px"><strong>showLightningOnFlowerBlockReturn</strong>&nbsp;(default = true): If lightning should be shown if a flower block is re-placed into the world.</span><br><span style="font-size:12px"><strong>preventMimicsOnPlacedAndBoneMealFlowers</strong>&nbsp;(default = true): Whether manually placed and bone mealed flowers should not spawn mimics.</span><br><span style="font-size:12px"><strong>checkForMimicAroundBlockRange</strong>&nbsp;(default = 15, min 1, max 30): How many blocks away from the player the mod should check for mimics. Increasing this will take more processing power.</span><br><span style="font-size:12px"><strong>mimicTransformAroundPlayerRange</strong>&nbsp;(default = 5, min 1, max 30): How close the player should be to a mimic flower in order for it to transform.</span><br><span style="font-size:12px"><strong>checkForMimicFlowersDelayInTicks</strong>&nbsp;(default = 50, min 1, max 3600000): How often the mod should check for flower mimics around the player. 20 ticks = 1 second</span><br><br><br><span style="font-size:18px">There are also two config files located in <em><strong>./config/flowermimics/. :</strong></em></span><br><br><span style="font-size:14px"><strong><span style="font-size:18px">It's possible to configure what flower mimics will turn into. Will also work with modded entities and flowers:</span><br></strong><em>./config/flowermimics/flower_mimics.txt</em></span><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/flower-mimics/a.png"></picture><br><br><span style="font-size:14px"><strong><span style="font-size:18px">You can change what items flower mimics will drop:</span><br></strong><em>./config/flowermimics/flower_item_drops.txt</em></span><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/flower-mimics/b.png"></picture></p>
<p><br>------------------<br><br><span style="font-size:24px"><strong>You may freely use this mod in any modpack, as long as the download remains hosted within the CurseForge or Modrinth ecosystem.</strong></span><br><br><span style="font-size:18px"><a style="font-size:18px;color:#008000" href="https://serilum.com/" rel="nofollow">Serilum.com</a> contains an overview and more information on all mods available.</span><br><br><span style="font-size:14px">Comments are disabled as I'm unable to keep track of all the separate pages on each mod.</span><span style="font-size:14px"><br>For issues, ideas, suggestions or anything else there is the&nbsp;<a style="font-size:14px;color:#008000" href="https://github.com/Serilum/.issue-tracker" rel="nofollow">Github repo</a>. Thanks!</span><span style="font-size:6px"><br><br></span></p>
<p style="text-align:center"><a href="https://serilum.com/donate" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/projects/support.svg" alt="" width="306" height="50"></a></p>